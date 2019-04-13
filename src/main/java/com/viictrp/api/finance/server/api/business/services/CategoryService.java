package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICategoryService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.CategoryConverter;
import com.viictrp.api.finance.server.api.domain.Category;
import com.viictrp.api.finance.server.api.dto.CategoryDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryConverter converter;

    private final CategoryRepository repository;

    private final UserService userService;

    public CategoryService(CategoryConverter converter, CategoryRepository repository, UserService userService) {
        this.converter = converter;
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public Category buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
    }

    @Override
    public CategoryDTO save(CategoryDTO dto, OAuthUser user) {
        Category category = converter.toEntity(dto);
        category.setUser(userService.buscarUsuarioPorId(user.getUserId()));
        Audity.audityEntity(category, user);
        return Optional.of(repository.save(category))
                .map(converter::toDto)
                .get();
    }
}
