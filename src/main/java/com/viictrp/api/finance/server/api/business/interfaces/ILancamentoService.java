package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

import java.util.List;

public interface ILancamentoService {

    LancamentoDTO salvar(LancamentoDTO lancamento, OAuthUser user);
    LancamentoDTO buscarLancamento(Long id);
    List<LancamentoDTO> buscarLancamentosByFatura(Long idFatura);
    List<LancamentoDTO> buscarLancamentosByCarteira(Long idCarteira, OAuthUser user);
}
