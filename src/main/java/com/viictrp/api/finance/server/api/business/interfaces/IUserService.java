package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Usuario;

import java.util.List;

public interface IUserService {

    Usuario buscarUsuarioPorId(Long id);
    List<Usuario> buscarUsuarios();
}
