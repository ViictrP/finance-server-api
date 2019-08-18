package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.IUsuarioService;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.persistence.UsuarioRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario buscarUsuario(ObjectId id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado para o ID fornecido"));
    }
}
