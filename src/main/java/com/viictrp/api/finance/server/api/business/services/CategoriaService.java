package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICategoriaService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.categoria.CategoriaConverter;
import com.viictrp.api.finance.server.api.domain.Categoria;
import com.viictrp.api.finance.server.api.dto.CategoriaDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.categoria.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements ICategoriaService {

    private final CategoriaConverter converter;
    private final CategoriaRepository repository;
    private final UserService userService;

    public CategoriaService(CategoriaConverter converter, CategoriaRepository repository, UserService userService) {
        this.converter = converter;
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public CategoriaDTO buscarPorId(Long id, OAuthUser user) {
        return repository.findByIdAndUsuarioId(id, user.getUsuarioId())
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
    }

    @Override
    public CategoriaDTO save(CategoriaDTO dto, OAuthUser user) {
        Categoria categoria = converter.toEntity(dto);
        categoria.setUsuario(userService.buscarUsuarioPorId(user.getUsuarioId()));
        Audity.audityEntity(user, categoria);
        repository.save(categoria);
        return converter.toDto(categoria);
    }

    @Override
    public List<CategoriaDTO> buscarCategorias(OAuthUser user) {
        return repository.findByUsuarioId(user.getUsuarioId())
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }
}
