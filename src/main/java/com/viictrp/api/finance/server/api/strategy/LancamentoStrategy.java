package com.viictrp.api.finance.server.api.strategy;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.business.interfaces.ILancamentoStrategyService;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import org.springframework.stereotype.Component;

@Component
public class LancamentoStrategy {

    private final ICarteiraService carteiraService;
    private final IFaturaService faturaService;

    public LancamentoStrategy(ICarteiraService carteiraService,
                              IFaturaService faturaService) {
        this.carteiraService = carteiraService;
        this.faturaService = faturaService;
    }

    public ILancamentoStrategyService map(LancamentoDTO lancamentoDTO) {
        return lancamentoDTO.getFatura() != null ? faturaService : carteiraService;
    }
}
