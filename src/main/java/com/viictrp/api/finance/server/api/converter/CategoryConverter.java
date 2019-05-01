package com.viictrp.api.finance.server.api.converter;

import com.viictrp.api.finance.server.api.domain.Category;
import com.viictrp.api.finance.server.api.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<Category, CategoryDTO> {

    private final UserConverter userConverter;

    public CategoryConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        Category entity = new Category();
        if (categoryDTO != null) {
            entity = new Category();
            entity.setTitle(categoryDTO.getTitle());
            entity.setDescription(categoryDTO.getDescription());
        }
        return entity;
    }

    @Override
    public CategoryDTO toDto(Category category) {
        CategoryDTO dto = null;
        if (category != null) {
            dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setTitle(category.getTitle());
            dto.setDescription(category.getDescription());
            dto.setUser(userConverter.toDto(category.getUser()));
            dto.setExcluido(category.getExcluido());
        }
        return dto;
    }
}
