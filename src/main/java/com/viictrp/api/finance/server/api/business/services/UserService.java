package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.IUserService;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.oauth.service.UserDetailsServiceImpl;
import com.viictrp.api.finance.server.api.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UsuarioRepository repository;

    private final UserDetailsServiceImpl service;

    public UserService(UsuarioRepository repository, @Qualifier("userDetailsService") UserDetailsServiceImpl service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        OAuthUser oAuthUser = service.loadByUserId(id);
        usuario.setOAuthUser(oAuthUser);
        return usuario;
    }

    @Override
    public List<Usuario> buscarUsuarios() {
        return repository.findAll()
                .stream()
                .peek(user -> user.setOAuthUser(service.loadByUserId(user.getId())))
                .collect(Collectors.toList());
    }
}
