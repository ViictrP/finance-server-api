package com.viictrp.api.finance.server.api.business;

import com.viictrp.api.finance.server.api.domain.User;

import java.util.List;

public interface IUserService {

    User buscarUsuarioPorId(Long id);
    List<User> buscarUsuarios();
}
