package com.viictrp.api.finance.server.api.converter.orcamento;

import com.viictrp.api.finance.server.api.converter.Converter;
import com.viictrp.api.finance.server.api.domain.Orcamento;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import org.springframework.stereotype.Component;

@Component
public class OrcamentoConverter implements Converter<Orcamento, OrcamentoDTO> {

    @Override
    public Orcamento toEntity(OrcamentoDTO orcamentoDTO) {
        Orcamento orcamento = null;
        if (orcamentoDTO != null) {
            orcamento = new Orcamento();
            orcamento.setMes(MesType.customValueOf(orcamentoDTO.getMes()));
            orcamento.setValor(orcamentoDTO.getValor());
        }
        return orcamento;
    }

    @Override
    public OrcamentoDTO toDto(Orcamento orcamento) {
        OrcamentoDTO orcamentoDTO = null;
        if (orcamento != null) {
            orcamentoDTO = new OrcamentoDTO();
            orcamentoDTO.setId(orcamento.getId().toString());
            orcamentoDTO.setMes(orcamento.getMes().name());
            orcamentoDTO.setValor(orcamento.getValor());
            orcamentoDTO.setCarteiraId(orcamento.getCarteiraId().toString());
            orcamentoDTO.setCreatedDate(orcamento.getCreateDate());
            orcamentoDTO.setCreatedBy(orcamento.getCreatedBy());
            orcamentoDTO.setLastModifiedBy(orcamento.getLastModifiedBy());
            orcamentoDTO.setLastModifiedDate(orcamento.getLastModifiedDate());
        }
        return orcamentoDTO;
    }
}
