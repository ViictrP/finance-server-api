package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

public interface IOrcamentoService {

    OrcamentoDTO salvarOrcamento(OrcamentoDTO orcamentoDTO, OAuthUser user);
}
