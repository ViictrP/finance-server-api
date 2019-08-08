package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

import java.util.List;

public interface IFaturaService extends ILancamentoStrategyService {

    FaturaDTO salvar(FaturaDTO faturaDTO, OAuthUser user);
    FaturaDTO buscarFatura(Long id);
    Fatura buscarFaturaEntity(Long idFatura);
    List<FaturaDTO> buscarFaturas(Long idCartao, OAuthUser user);
}
