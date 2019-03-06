package com.viictrp.api.finance.server.api.business;

import com.viictrp.api.finance.server.api.domain.User;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.oauth.service.UserDetailsServiceImpl;
import com.viictrp.api.finance.server.api.persistence.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsServiceImpl service;

    @Override
    public User buscarUsuarioPorId(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        OAuthUser oAuthUser = service.loadByUserId(id);
        user.setOAuthUser(oAuthUser);
        return user;
    }
}
