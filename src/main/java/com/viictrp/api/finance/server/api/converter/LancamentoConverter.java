package com.viictrp.api.finance.server.api.converter;

import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class LancamentoConverter implements Converter<Lancamento, LancamentoDTO> {

    @Override
    public Lancamento toEntity(LancamentoDTO lancamentoDTO) {
        Lancamento lancamento = null;
        if (lancamentoDTO != null) {
            lancamento = new Lancamento();
            lancamento.setDescricao(lancamentoDTO.getDescricao());
            lancamento.setValor(lancamentoDTO.getValor());
            lancamento.setData(lancamentoDTO.getData());
            lancamento.setCategoriaId(new ObjectId(lancamentoDTO.getCategoriaId()));
            lancamento.setQuantidadeParcelas(lancamentoDTO.getQuantidadeParcelas());
        }
        return lancamento;
    }

    @Override
    public LancamentoDTO toDto(Lancamento lancamento) {
        LancamentoDTO lancamentoDTO = null;
        if (lancamento != null) {
            lancamentoDTO = new LancamentoDTO();
            lancamentoDTO.setId(lancamento.getId().toString());
            lancamentoDTO.setDescricao(lancamento.getDescricao());
            lancamentoDTO.setValor(lancamento.getValor());
            lancamentoDTO.setData(lancamento.getData());
            lancamentoDTO.setCategoriaId(lancamento.getCategoriaId().toString());

            if (lancamento.getCarteiraId() != null) {
                lancamentoDTO.setCarteiraId(lancamento.getCarteiraId().toString());
            }

            if (lancamento.getFaturaId() != null) {
                lancamentoDTO.setFaturaId(lancamento.getFaturaId().toString());
            }

            lancamentoDTO.setCreatedDate(lancamento.getCreateDate());
            lancamentoDTO.setCreatedBy(lancamento.getCreatedBy());
            lancamentoDTO.setLastModifiedBy(lancamento.getLastModifiedBy());
            lancamentoDTO.setLastModifiedDate(lancamento.getLastModifiedDate());
        }
        return lancamentoDTO;
    }
}
