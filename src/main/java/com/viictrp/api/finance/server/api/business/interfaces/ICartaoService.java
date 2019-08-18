package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.CartaoDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICartaoService {

    Mono<CartaoDTO> salvar(CartaoDTO cartaoDTO, OAuthUser user);

    Mono<CartaoDTO> buscarCartao(ObjectId id, OAuthUser user);

    Flux<CartaoDTO> buscarCartoes(OAuthUser user);
}
