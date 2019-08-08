package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

public interface IOrcamentoService {

    OrcamentoDTO salvar(OrcamentoDTO orcamentoDTO, OAuthUser user);
    OrcamentoDTO buscarOrcamento(Long carteiraID, Long orcamentoID, OAuthUser user);
}
