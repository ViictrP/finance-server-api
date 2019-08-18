package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.business.interfaces.IOrcamentoService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.OrcamentoConverter;
import com.viictrp.api.finance.server.api.domain.Orcamento;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.OrcamentoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrcamentoService implements IOrcamentoService {

    private static final Mono<OrcamentoDTO> NOT_FOUND_FALLBACK = Mono.error(new ResourceNotFoundException("Orçamento não encontrado para ID fornecido."));

    private final OrcamentoConverter converter;
    private final OrcamentoRepository repository;
    private final ICarteiraService carteiraService;

    public OrcamentoService(OrcamentoConverter converter,
                            OrcamentoRepository repository,
                            ICarteiraService carteiraService) {
        this.converter = converter;
        this.repository = repository;
        this.carteiraService = carteiraService;
    }

    @Override
    public Mono<OrcamentoDTO> salvar(ObjectId carteiraId, OrcamentoDTO orcamentoDTO, OAuthUser user) {
        Mono<OrcamentoDTO> novoOrcamentoMono = Mono.just(orcamentoDTO);
        return carteiraService.buscarCarteira(carteiraId, user)
                .flatMap(carteiraDTO -> repository.findByMes(MesType.customValueOf(orcamentoDTO.getMes()))
                        .map(orcamento -> {
                            orcamento.mergeDados(converter.toEntity(orcamentoDTO), user);
                            return orcamento;
                        })
                        .flatMap(this.repository::save)
                        .map(converter::toDto)
                        .switchIfEmpty(novoOrcamentoMono
                                .map(novoOrcamento -> {
                                    Orcamento orcamento = converter.toEntity(novoOrcamento);
                                    orcamento.setCarteiraId(carteiraId);
                                    Audity.audityEntity(user, orcamento);
                                    return orcamento;
                                }).flatMap(this.repository::save)
                                .map(converter::toDto)
                        )
                );
    }

    @Override
    public Mono<OrcamentoDTO> buscarOrcamento(ObjectId carteiraID, ObjectId orcamentoID, OAuthUser user) {
        return repository.findByCarteiraIdAndId(carteiraID, orcamentoID)
                .map(converter::toDto)
                .switchIfEmpty(NOT_FOUND_FALLBACK);
    }

}
