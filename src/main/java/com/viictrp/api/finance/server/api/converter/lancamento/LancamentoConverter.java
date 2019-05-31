package com.viictrp.api.finance.server.api.converter.lancamento;

import com.viictrp.api.finance.server.api.converter.Converter;
import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import org.springframework.stereotype.Component;

@Component
public class LancamentoConverter implements Converter<Lancamento, LancamentoDTO> {

    @Override
    public Lancamento toEntity(LancamentoDTO lancamentoDTO) {
        return null;
    }

    @Override
    public LancamentoDTO toDto(Lancamento lancamento) {
        return null;
    }
}
