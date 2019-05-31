package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Cartao;
import com.viictrp.api.finance.server.api.dto.CartaoDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

import java.util.List;

public interface ICartaoService {

    CartaoDTO salvar(CartaoDTO cartaoDTO, OAuthUser user);
    Cartao buscarCartao(Long id, OAuthUser user);
    List<CartaoDTO> buscarCartoes(OAuthUser user);
}
