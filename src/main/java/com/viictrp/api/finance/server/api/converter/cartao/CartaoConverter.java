package com.viictrp.api.finance.server.api.converter.cartao;

import com.viictrp.api.finance.server.api.converter.Converter;
import com.viictrp.api.finance.server.api.domain.Cartao;
import com.viictrp.api.finance.server.api.dto.CartaoDTO;
import org.springframework.stereotype.Component;

@Component
public class CartaoConverter implements Converter<Cartao, CartaoDTO> {

    @Override
    public Cartao toEntity(CartaoDTO cartaoDTO) {
        Cartao cartao = null;
        if (cartaoDTO != null) {
            cartao = new Cartao();
            cartao.setDescricao(cartaoDTO.getDescricao());
            cartao.setLimite(cartaoDTO.getLimite());
            cartao.setDataFechamento(cartaoDTO.getDataFechamento());
        }
        return cartao;
    }

    @Override
    public CartaoDTO toDto(Cartao cartao) {
        CartaoDTO cartaoDTO = null;
        if (cartao != null) {
            cartaoDTO = new CartaoDTO();
            cartaoDTO.setDescricao(cartao.getDescricao());
            cartaoDTO.setId(cartao.getId());
            cartaoDTO.setLimite(cartaoDTO.getLimite());
            cartaoDTO.setDataFechamento(cartao.getDataFechamento());
        }
        return cartaoDTO;
    }
}
