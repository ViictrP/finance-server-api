package com.viictrp.api.finance.server.api.oauth.service;

import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.oauth.repository.IUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserDetailsRepository repository;

    public UserDetailsServiceImpl(IUserDetailsRepository repository) {
        this.repository = repository;
    }

    public OAuthUser save(OAuthUser user) {
        return repository.save(user);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public OAuthUser loadByUserId(Long id) {
        return repository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário de autenticação não encontrado"));
    }

    public List<OAuthUser> loadUsers() {
        return repository.findAll();
    }
}
