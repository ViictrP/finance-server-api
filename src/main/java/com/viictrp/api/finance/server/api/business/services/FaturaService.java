package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICartaoService;
import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.business.interfaces.ILancamentoService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.common.DateUtils;
import com.viictrp.api.finance.server.api.common.PreconditionsRest;
import com.viictrp.api.finance.server.api.converter.fatura.FaturaConverter;
import com.viictrp.api.finance.server.api.domain.Cartao;
import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.fatura.FaturaRepository;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaturaService implements IFaturaService {

    private static final String FATURA_NOT_FOUND = "A fatura não foi encontrada para o ID fornecido";

    private final FaturaRepository repository;
    private final ICartaoService cartaoService;
    private final FaturaConverter converter;

    public FaturaService(FaturaRepository repository,
                         ICartaoService cartaoService,
                         FaturaConverter converter) {
        this.repository = repository;
        this.cartaoService = cartaoService;
        this.converter = converter;
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
    public Lancamento salvarLancamentoNaFatura(Lancamento lancamento) {
        Fatura fatura = this.repository.findByMes(DateUtils.getMonthName())
                .orElseThrow(() -> new ResourceNotFoundException(FATURA_NOT_FOUND));
        Integer dia = LocalDate.now().getDayOfMonth();
        if (fatura.getDiaFechamento() <= dia) {
            fatura = this.repository.findByMes(MesType.nextMonth(fatura.getMes()))
                    .orElseThrow(() -> new ResourceNotFoundException(FATURA_NOT_FOUND));
        }
        fatura.addLancamento(lancamento);
        repository.save(fatura);
        return lancamento;
    }

    @Override
    public List<FaturaDTO> buscarFaturas(Long idCartao, OAuthUser user) {
        Cartao cartao = cartaoService.buscarCartao(idCartao, user);
        return repository.findByCartao(cartao)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }
}
