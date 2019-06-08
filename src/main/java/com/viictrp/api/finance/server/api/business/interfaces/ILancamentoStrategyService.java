package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;

import java.util.List;

public interface ILancamentoStrategyService {

    LancamentoDTO salvarLancamento(Lancamento lancamento);
    List<LancamentoDTO> salvarLancamentoComParcelas(Lancamento lancamento);
}
