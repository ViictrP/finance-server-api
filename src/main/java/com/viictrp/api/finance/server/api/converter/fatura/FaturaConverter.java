package com.viictrp.api.finance.server.api.converter.fatura;

import com.viictrp.api.finance.server.api.converter.Converter;
import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import org.springframework.stereotype.Component;

@Component
public class FaturaConverter implements Converter<Fatura, FaturaDTO> {

    @Override
    public Fatura toEntity(FaturaDTO faturaDTO) {
        Fatura fatura = null;
        if (faturaDTO != null) {
            fatura = new Fatura();
            fatura.setMes(faturaDTO.getMes() != null ? MesType.customValueOf(faturaDTO.getMes()) : MesType.JANEIRO);
            fatura.setDiaFechamento(faturaDTO.getDiaFechamento());
            fatura.setDescricao(faturaDTO.getDescricao());
            fatura.setPago(faturaDTO.getPago());
            fatura.setTitulo(faturaDTO.getTitulo());
        }
        return fatura;
    }

    @Override
    public FaturaDTO toDto(Fatura fatura) {
        FaturaDTO faturaDTO = null;
        if (fatura != null) {
            faturaDTO = new FaturaDTO();
            faturaDTO.setId(fatura.getId().toString());
            faturaDTO.setDescricao(fatura.getDescricao());
            faturaDTO.setMes(fatura.getMes().name());
            faturaDTO.setDiaFechamento(fatura.getDiaFechamento());
            faturaDTO.setPago(fatura.getPago());
            faturaDTO.setTitulo(fatura.getTitulo());
            faturaDTO.setCartaoId(fatura.getCartaoId().toString());
        }
        return faturaDTO;
    }
}
