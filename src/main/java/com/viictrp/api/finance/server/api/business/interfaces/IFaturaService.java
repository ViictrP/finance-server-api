package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFaturaService {

    Mono<FaturaDTO> salvar(ObjectId cartaoId, FaturaDTO faturaDTO, OAuthUser user);

    Mono<FaturaDTO> buscarFatura(ObjectId id);

    Mono<FaturaDTO> buscarFaturaProximoMes(FaturaDTO faturaDTO);

    Flux<FaturaDTO> buscarFaturas(ObjectId idCartao, OAuthUser user);
}
