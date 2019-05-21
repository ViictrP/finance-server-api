package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICategoriaService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.CategoryConverter;
import com.viictrp.api.finance.server.api.domain.Categoria;
import com.viictrp.api.finance.server.api.dto.CategoryDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements ICategoriaService {

    private final CategoryConverter converter;

    private final CategoriaRepository repository;

    private final UserService userService;

    public CategoriaService(CategoryConverter converter, CategoriaRepository repository, UserService userService) {
        this.converter = converter;
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public Categoria buscarPorId(Long id, OAuthUser user) {
        return repository.findByIdAndUsuarioId(id, user.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
    }

    @Override
    public CategoryDTO save(CategoryDTO dto, OAuthUser user) {
        Categoria categoria = converter.toEntity(dto);
        categoria.setUsuario(userService.buscarUsuarioPorId(user.getUsuarioId()));
        Audity.audityEntity(categoria, user);
        repository.save(categoria);
        return converter.toDto(categoria);
    }
}
