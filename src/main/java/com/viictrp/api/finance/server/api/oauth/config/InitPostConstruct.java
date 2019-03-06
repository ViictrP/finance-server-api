package com.viictrp.api.finance.server.api.oauth.config;

import com.viictrp.api.finance.server.api.domain.User;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.oauth.service.UserDetailsServiceImpl;
import com.viictrp.api.finance.server.api.persistence.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitPostConstruct {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostConstruct
    public void init() {
        this.createUser();
    }

    private void createUser() {

        System.out.println("######################################################");
        System.out.println("######################################################");
        System.out.println("################ [CRIANDO USUÁRIOS] ##################");

        userRepository.deleteAll();
        userDetailsService.deleteAll();

        User user = new User();
        user.setName("Victor");
        user.setLastname("Prado");
        user.setEmail("vpradodev@gmail.com");
        user.setAge(26);
        user.setCreatedBy("system");
        user.setLastModifiedBy("system");
        user.setCreateDate(LocalDate.now());
        user.setLastUpdateDate(LocalDate.now());

        User jpaEntity = userRepository.save(user);

        System.out.println("######################################################");
        System.out.println("######################################################");
        System.out.println("############### [USUÁRIO JPA CRIADO] #################");

        System.out.println(jpaEntity.toString());

        GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");

        OAuthUser oAuthUser = new OAuthUser();
        oAuthUser.getAuthorities().add(simpleGrantedAuthority);
        oAuthUser.setAccountNonLocked(true);
        oAuthUser.setPassword(encoder.encode("12345678"));
        oAuthUser.setMaster(true);

        oAuthUser.setUser(jpaEntity);

        OAuthUser mongoEntity = userDetailsService.save(oAuthUser);

        System.out.println("######################################################");
        System.out.println("######################################################");
        System.out.println("############## [USUÁRIO OAUTH CRIADO] ################");

        System.out.println(mongoEntity.toString());
    }
}
