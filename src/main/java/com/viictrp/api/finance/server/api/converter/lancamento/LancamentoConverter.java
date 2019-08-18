package com.viictrp.api.finance.server.api.converter.lancamento;

import com.viictrp.api.finance.server.api.converter.Converter;
import com.viictrp.api.finance.server.api.converter.carteira.CarteiraConverter;
import com.viictrp.api.finance.server.api.converter.categoria.CategoriaConverter;
import com.viictrp.api.finance.server.api.converter.fatura.FaturaConverter;
import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class LancamentoConverter implements Converter<Lancamento, LancamentoDTO> {

    private final CategoriaConverter categoriaConverter;
    private final CarteiraConverter carteiraConverter;
    private final FaturaConverter faturaConverter;

    public LancamentoConverter(CategoriaConverter categoriaConverter,
                               CarteiraConverter carteiraConverter,
                               FaturaConverter faturaConverter) {
        this.categoriaConverter = categoriaConverter;
        this.carteiraConverter = carteiraConverter;
        this.faturaConverter = faturaConverter;
    }

    @Override
    public Lancamento toEntity(LancamentoDTO lancamentoDTO) {
        Lancamento lancamento = null;
        if (lancamentoDTO != null) {
            lancamento = new Lancamento();
            lancamento.setDescricao(lancamentoDTO.getDescricao());
            lancamento.setValor(lancamentoDTO.getValor());
            lancamento.setData(lancamentoDTO.getData());
            lancamento.setCategoriaId(new ObjectId(lancamentoDTO.getCategoriaId()));
            lancamento.setCarteiraId(new ObjectId(lancamentoDTO.getCarteiraId()));
            lancamento.setFaturaId(new ObjectId(lancamentoDTO.getFaturaId()));
            lancamento.setQuantidadeParcelas(lancamentoDTO.getQuantidadeParcelas());
        }
        return lancamento;
    }

    @Override
    public LancamentoDTO toDto(Lancamento lancamento) {
        LancamentoDTO lancamentoDTO = null;
        if (lancamento != null) {
            lancamentoDTO = new LancamentoDTO();
            lancamentoDTO.setId(lancamento.getId());
            lancamentoDTO.setDescricao(lancamento.getDescricao());
            lancamentoDTO.setValor(lancamento.getValor());
            lancamentoDTO.setData(lancamento.getData());
            lancamentoDTO.setCategoriaId(lancamento.getCategoriaId().toString());
            lancamentoDTO.setCarteiraId(lancamento.getCarteiraId().toString());
            lancamentoDTO.setFaturaId(lancamento.getFaturaId().toString());
            lancamentoDTO.setCreatedDate(lancamento.getCreateDate());
            lancamentoDTO.setCreatedBy(lancamento.getCreatedBy());
            lancamentoDTO.setLastModifiedBy(lancamento.getLastModifiedBy());
            lancamentoDTO.setLastModifiedDate(lancamento.getLastModifiedDate());
        }
        return lancamentoDTO;
    }
}
