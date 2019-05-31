package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

import java.util.List;

public interface ILancamentoService {

    LancamentoDTO salvar(LancamentoDTO lancamento, OAuthUser user);
    LancamentoDTO buscarLancamento(Long id, OAuthUser user);
    List<LancamentoDTO> buscarLancamentos(Long idCartao, OAuthUser user);
}
