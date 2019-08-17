package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;

public interface IOrcamentoService {

    OrcamentoDTO salvar(ObjectId carteiraId, OrcamentoDTO orcamentoDTO, OAuthUser user);

    OrcamentoDTO buscarOrcamento(ObjectId carteiraID, ObjectId orcamentoID, OAuthUser user);
}
