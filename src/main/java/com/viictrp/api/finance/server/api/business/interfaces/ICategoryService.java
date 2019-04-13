package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Category;
import com.viictrp.api.finance.server.api.dto.CategoryDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

public interface ICategoryService {

    Category buscarPorId(Long id);
    CategoryDTO save(CategoryDTO dto, OAuthUser user);
}
