package com.viictrp.api.finance.server.api.converter.categoria;

import com.viictrp.api.finance.server.api.converter.Converter;
import com.viictrp.api.finance.server.api.converter.user.UserConverter;
import com.viictrp.api.finance.server.api.domain.Categoria;
import com.viictrp.api.finance.server.api.dto.CategoriaDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoriaConverter implements Converter<Categoria, CategoriaDTO> {

    private final UserConverter userConverter;

    public CategoriaConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        Categoria entity = new Categoria();
        if (categoriaDTO != null) {
            entity = new Categoria();
            entity.setTitulo(categoriaDTO.getTitle());
            entity.setDescricao(categoriaDTO.getDescription());
        }
        return entity;
    }

    @Override
    public CategoriaDTO toDto(Categoria categoria) {
        CategoriaDTO dto = null;
        if (categoria != null) {
            dto = new CategoriaDTO();
            dto.setId(categoria.getId());
            dto.setTitle(categoria.getTitulo());
            dto.setDescription(categoria.getDescricao());
            dto.setUser(userConverter.toDto(categoria.getUsuario()));
        }
        return dto;
    }
}
