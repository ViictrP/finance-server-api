package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICategoriaService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.categoria.CategoriaConverter;
import com.viictrp.api.finance.server.api.domain.Categoria;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.dto.CategoriaDTO;
import com.viictrp.api.finance.server.api.exception.ResourceBusinessException;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.categoria.CategoriaRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoriaService implements ICategoriaService {

    private static final String CATEGORIA_NOT_FOUND = "Categoria não encontrada";

    private final CategoriaConverter converter;
    private final CategoriaRepository repository;
    private final UserService userService;

    public CategoriaService(CategoriaConverter converter, CategoriaRepository repository, UserService userService) {
        this.converter = converter;
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public Mono<CategoriaDTO> buscarPorId(ObjectId id, OAuthUser user) {
        return repository.findByIdAndUsuarioId(id, user.getUsuarioId())
                .map(converter::toDto)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(CATEGORIA_NOT_FOUND)));
    }

    @Override
    public Mono<CategoriaDTO> save(CategoriaDTO dto, OAuthUser user) {
        Usuario usuario = userService.buscarUsuarioPorId(user.getUsuarioId());
        Categoria categoria = converter.toEntity(dto);
        categoria.setUsuarioId(usuario.getId());
        Audity.audityEntity(user, categoria);
        return repository.save(categoria).map(converter::toDto);
    }

    @Override
    public Flux<CategoriaDTO> buscarCategorias(OAuthUser user) {
        return repository.findByUsuarioId(user.getUsuarioId())
                .map(converter::toDto);
    }
}
