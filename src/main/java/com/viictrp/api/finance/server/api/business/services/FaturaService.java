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
import com.viictrp.api.finance.server.api.persistence.fatura.FaturaRepository;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        Fatura fatura = this.repository.findById(lancamento.getId())
                .orElseThrow(() -> new ResourceNotFoundException(FATURA_NOT_FOUND));
        MesType mes = DateUtils.getMonthName(lancamento.getData());
        if (DateUtils.isTodayAfterThan(fatura.getDiaFechamento())) {
            fatura = this.repository.findByMesAndCartao(MesType.nextMonth(mes), fatura.getCartao())
                    .orElseGet(Fatura::new);
        }
        if (fatura.isNew()) {
            //TODO continuar, gerar nova fatura caso não tenha a próxima fatura
        }
        fatura.addLancamento(lancamento);
        repository.save(fatura);
        return lancamentoConverter.toDto(lancamento);
    }

    //TODO continuar a lógica de lançamentos parcelados
    @Override
    public List<LancamentoDTO> salvarLancamentoComparcelas(Lancamento lancamento) {
        MesType primeiroMes = DateUtils.getMonthName(lancamento.getData());
        for (int i = 0; i < lancamento.getQuantidadeParcelas(); i++) {
            Lancamento lanc = new Lancamento();
            lanc.setData(lanc.getData());
            lanc.setCategoria(lanc.getCategoria());
            lanc.setValor(lanc.getValor() / lanc.getQuantidadeParcelas());
            lanc.setDescricao(lanc.getDescricao() + " " + i + "/" + lancamento.getQuantidadeParcelas());
            lanc.setCodigoParcela(DateTime.now().toString() + i + "-" + lancamento.getQuantidadeParcelas());
            lanc.setFatura(lancamento.getFatura());
        }
        return Collections.singletonList(salvarLancamento(lancamento));
    }
}
