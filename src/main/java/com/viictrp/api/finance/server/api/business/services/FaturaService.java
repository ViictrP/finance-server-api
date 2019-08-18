package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICartaoService;
import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.FaturaConverter;
import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.FaturaRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FaturaService implements IFaturaService {

    private static final String FATURA_NOT_FOUND = "A fatura não foi encontrada para o ID fornecido";
    private static final Mono<FaturaDTO> NOT_FOUND_FALLBACK = Mono.error(new ResourceNotFoundException(FATURA_NOT_FOUND));

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
    public Mono<FaturaDTO> salvar(ObjectId cartaoId, FaturaDTO faturaDTO, OAuthUser user) {
        return cartaoService.buscarCartao(cartaoId, user)
                .flatMap(cartaoDTO -> {
                    Fatura fatura = converter.toEntity(faturaDTO);
                    fatura.setCartaoId(cartaoId);
                    Audity.audityEntity(user, fatura);
                    return repository.save(fatura).map(converter::toDto);
                });

    }

    @Override
    public Mono<FaturaDTO> buscarFatura(ObjectId id) {
        return repository.findById(id)
                .map(converter::toDto)
                .switchIfEmpty(NOT_FOUND_FALLBACK);
    }

    @Override
    public Mono<FaturaDTO> buscarFaturaProximoMes(FaturaDTO faturaDTO) {
        Fatura proximaFatura = nextMonthFaturaFromFatura(faturaDTO);
        Mono<Fatura> novaFatura = Mono.just(proximaFatura);
        return repository.findByMes(proximaFatura.getMes())
                .map(converter::toDto)
                .switchIfEmpty(novaFatura.flatMap(this.repository::save)
                        .map(converter::toDto)
                );
    }

    @Override
    public Flux<FaturaDTO> buscarFaturas(ObjectId idCartao, OAuthUser user) {
        return repository.findByCartaoId(idCartao)
                .map(converter::toDto);
    }

    private Fatura nextMonthFaturaFromFatura(FaturaDTO faturaDTO) {
        Fatura proximaFatura = new Fatura();
        proximaFatura.setCartaoId(new ObjectId(faturaDTO.getCartaoId()));
        proximaFatura.setDescricao(faturaDTO.getDescricao());
        proximaFatura.setDiaFechamento(faturaDTO.getDiaFechamento());
        proximaFatura.setTitulo(faturaDTO.getTitulo());
        proximaFatura.setMes(MesType.nextMonth(MesType.customValueOf(faturaDTO.getMes())));
        proximaFatura.setCreateDate(faturaDTO.getCreatedDate());
        proximaFatura.setCreatedBy(faturaDTO.getCreatedBy());
        proximaFatura.setLastModifiedDate(faturaDTO.getLastModifiedDate());
        proximaFatura.setLastModifiedBy(faturaDTO.getLastModifiedBy());
        return proximaFatura;
    }
}
