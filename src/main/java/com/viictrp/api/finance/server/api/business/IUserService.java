package com.viictrp.api.finance.server.api.business;

import com.viictrp.api.finance.server.api.domain.User;

public interface IUserService {

    User buscarUsuarioPorId(Long id);
}
