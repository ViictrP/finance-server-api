package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Categoria;
import com.viictrp.api.finance.server.api.dto.CategoryDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

public interface ICategoriaService {

    Categoria buscarPorId(Long id, OAuthUser user);
    CategoryDTO save(CategoryDTO dto, OAuthUser user);
}
