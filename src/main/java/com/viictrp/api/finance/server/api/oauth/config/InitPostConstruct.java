package com.viictrp.api.finance.server.api.oauth.config;

import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.oauth.service.UserDetailsServiceImpl;
import com.viictrp.api.finance.server.api.persistence.usuario.UsuarioRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitPostConstruct {

    private final UserDetailsServiceImpl userDetailsService;

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public InitPostConstruct(@Qualifier("userDetailsService") UserDetailsServiceImpl userDetailsService,
                             PasswordEncoder encoder,
                             UsuarioRepository usuarioRepository) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
        this.usuarioRepository = usuarioRepository;
    }

    @PostConstruct
    public void init() {
        this.createUser();
    }

    private void createUser() {

        System.out.println("######################################################");
        System.out.println("######################################################");
        System.out.println("################ [CRIANDO USUÁRIOS] ##################");

        usuarioRepository.deleteAll();
        userDetailsService.deleteAll();

        Usuario usuario = new Usuario();
        usuario.setName("Victor");
        usuario.setLastname("Prado");
        usuario.setEmail("vpradodev@gmail.com");
        usuario.setAge(26);
        usuario.setCreatedBy("system");
        usuario.setLastModifiedBy("system");
        usuario.setCreateDate(DateTime.now());
        usuario.setLastUpdateDate(DateTime.now());

        Usuario jpaEntity = usuarioRepository.save(usuario);

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
