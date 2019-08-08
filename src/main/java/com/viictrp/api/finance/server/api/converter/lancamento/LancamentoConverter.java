package com.viictrp.api.finance.server.api.converter.lancamento;

import com.viictrp.api.finance.server.api.converter.Converter;
import com.viictrp.api.finance.server.api.converter.carteira.CarteiraConverter;
import com.viictrp.api.finance.server.api.converter.categoria.CategoriaConverter;
import com.viictrp.api.finance.server.api.converter.fatura.FaturaConverter;
import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
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
            lancamento.setCategoria(categoriaConverter.toEntity(lancamentoDTO.getCategoria()));
            lancamento.setCarteira(carteiraConverter.toEntity(lancamentoDTO.getCarteira()));
            lancamento.setFatura(faturaConverter.toEntity(lancamentoDTO.getFatura()));
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
            lancamentoDTO.setData(lancamentoDTO.getData());
            lancamentoDTO.setCategoria(categoriaConverter.toDto(lancamento.getCategoria()));
            lancamentoDTO.setCarteira(carteiraConverter.toDto(lancamento.getCarteira()));
            lancamentoDTO.setFatura(faturaConverter.toDto(lancamento.getFatura()));
        }
        return lancamentoDTO;
    }
}
