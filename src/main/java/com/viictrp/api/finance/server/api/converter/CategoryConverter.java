package com.viictrp.api.finance.server.api.converter;

import com.viictrp.api.finance.server.api.domain.Categoria;
import com.viictrp.api.finance.server.api.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<Categoria, CategoryDTO> {

    private final UserConverter userConverter;

    public CategoryConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public Categoria toEntity(CategoryDTO categoryDTO) {
        Categoria entity = new Categoria();
        if (categoryDTO != null) {
            entity = new Categoria();
            entity.setTitulo(categoryDTO.getTitle());
            entity.setDescricao(categoryDTO.getDescription());
        }
        return entity;
    }

    @Override
    public CategoryDTO toDto(Categoria categoria) {
        CategoryDTO dto = null;
        if (categoria != null) {
            dto = new CategoryDTO();
            dto.setId(categoria.getId());
            dto.setTitle(categoria.getTitulo());
            dto.setDescription(categoria.getDescricao());
            dto.setUser(userConverter.toDto(categoria.getUsuario()));
            dto.setExcluido(categoria.getExcluido());
        }
        return dto;
    }
}
