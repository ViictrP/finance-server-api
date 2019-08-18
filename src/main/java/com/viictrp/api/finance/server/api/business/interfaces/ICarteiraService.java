package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICarteiraService {

    Mono<CarteiraDTO> salvar(CarteiraDTO carteiraDTO, OAuthUser user);

    Mono<CarteiraDTO> buscarCarteira(ObjectId id, OAuthUser user);

    Flux<CarteiraDTO> buscarPorUsuario(OAuthUser user);
}
