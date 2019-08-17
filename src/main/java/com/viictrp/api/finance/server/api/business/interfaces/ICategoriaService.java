package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.CategoriaDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICategoriaService {

    Mono<CategoriaDTO> buscarPorId(ObjectId id, OAuthUser user);

    Mono<CategoriaDTO> save(CategoriaDTO dto, OAuthUser user);

    Flux<CategoriaDTO> buscarCategorias(OAuthUser user);
}
