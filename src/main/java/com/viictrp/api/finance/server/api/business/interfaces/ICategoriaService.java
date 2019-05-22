package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.CategoriaDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

import java.util.List;

public interface ICategoriaService {

    CategoriaDTO buscarPorId(Long id, OAuthUser user);
    CategoriaDTO save(CategoriaDTO dto, OAuthUser user);
    List<CategoriaDTO> buscarCategorias(OAuthUser user);
}
