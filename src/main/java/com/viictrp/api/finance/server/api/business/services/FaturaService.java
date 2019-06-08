package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICartaoService;
import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.common.DateUtils;
import com.viictrp.api.finance.server.api.common.PreconditionsRest;
import com.viictrp.api.finance.server.api.converter.fatura.FaturaConverter;
import com.viictrp.api.finance.server.api.converter.lancamento.LancamentoConverter;
import com.viictrp.api.finance.server.api.domain.Cartao;
import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import com.viictrp.api.finance.server.api.persistence.fatura.FaturaRepository;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaturaService implements IFaturaService {

    private static final String FATURA_NOT_FOUND = "A fatura não foi encontrada para o ID fornecido";

    private final FaturaRepository repository;
    private final ICartaoService cartaoService;
    private final FaturaConverter converter;
    private final LancamentoConverter lancamentoConverter;

    public FaturaService(FaturaRepository repository,
                         ICartaoService cartaoService,
                         FaturaConverter converter, LancamentoConverter lancamentoConverter) {
        this.repository = repository;
        this.cartaoService = cartaoService;
        this.converter = converter;
        this.lancamentoConverter = lancamentoConverter;
    }

    @Override
    public FaturaDTO salvar(FaturaDTO faturaDTO, OAuthUser user) {
        PreconditionsRest.checkCondition(faturaDTO.getCartao() != null, "Por favor informe o cartão");
        Cartao cartao = cartaoService.buscarCartao(faturaDTO.getCartao().getId(), user);
        Fatura fatura = converter.toEntity(faturaDTO);
        cartao.addFatura(fatura);
        Audity.audityEntity(user, fatura, cartao);
        repository.save(fatura);
        return converter.toDto(fatura);
    }

    @Override
    public FaturaDTO buscarFatura(Long id) {
        return repository.findById(id)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(FATURA_NOT_FOUND));
    }

    @Override
    public Fatura buscarFaturaEntity(Long idFatura) {
        return repository.findById(idFatura)
                .orElseThrow(() -> new ResourceNotFoundException(FATURA_NOT_FOUND));
    }

    @Override
    public List<FaturaDTO> buscarFaturas(Long idCartao, OAuthUser user) {
        Cartao cartao = cartaoService.buscarCartao(idCartao, user);
        return repository.findByCartao(cartao)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LancamentoDTO salvarLancamento(Lancamento lancamento) {
        Fatura fatura = getFatura(lancamento);
        fatura.addLancamento(lancamento);
        repository.save(fatura);
        return lancamentoConverter.toDto(lancamento);
    }

    @Override
    public List<LancamentoDTO> salvarLancamentoComParcelas(Lancamento lancamento) {
        Fatura fatura = getFatura(lancamento);
        List<LancamentoDTO> lancamentos = new ArrayList<>();
        for (int i = 0; i < lancamento.getQuantidadeParcelas(); i++) {
            int index = i + 1;
            Lancamento lanc = new Lancamento();
            lanc.setData(lancamento.getData());
            lanc.setCategoria(lancamento.getCategoria());
            lanc.setValor(lancamento.getValor() / lancamento.getQuantidadeParcelas());
            lanc.setDescricao(lancamento.getDescricao() + " " + index + "/" + lancamento.getQuantidadeParcelas());
            lanc.setCodigoParcela(DateTime.now().toString() + index + "-" + lancamento.getQuantidadeParcelas());
            fatura.addLancamento(lanc);
            Audity.audityEntity(SecurityContext.getUser(), fatura, lanc);
            repository.save(fatura);
            lancamentos.add(lancamentoConverter.toDto(lanc));
            fatura = next(fatura);
        }
        return lancamentos;
    }

    private Fatura getFatura(Lancamento lancamento) {
        final Fatura fatura = this.repository.findById(lancamento.getFatura().getId())
                .orElseThrow(() -> new ResourceNotFoundException(FATURA_NOT_FOUND));
        if (DateUtils.isTodayAfterThan(fatura.getDiaFechamento())) {
            return next(fatura);
        }
        return fatura;
    }

    private Fatura next(Fatura fatura) {
        return this.repository.findByMesAndCartao(MesType.nextMonth(fatura.getMes()), fatura.getCartao())
                .orElseGet(() -> proximaFatura(fatura));
    }

    private Fatura proximaFatura(Fatura faturaAtual) {
        Fatura fatura = new Fatura();
        fatura.setDiaFechamento(faturaAtual.getDiaFechamento());
        fatura.setMes(MesType.nextMonth(faturaAtual.getMes()));
        fatura.setCartao(faturaAtual.getCartao());
        fatura.setTitulo("Fatura de " + fatura.getMes().name());
        fatura.setDescricao("Fatura do mês de " + fatura.getMes().name());
        return fatura;
    }
}
