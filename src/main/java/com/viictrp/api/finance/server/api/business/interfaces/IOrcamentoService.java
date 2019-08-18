package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

public interface IOrcamentoService {

    Mono<OrcamentoDTO> salvar(ObjectId carteiraId, OrcamentoDTO orcamentoDTO, OAuthUser user);

    Mono<OrcamentoDTO> buscarOrcamento(ObjectId carteiraID, ObjectId orcamentoID, OAuthUser user);
}
