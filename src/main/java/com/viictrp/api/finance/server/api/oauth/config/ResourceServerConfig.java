package com.viictrp.api.finance.server.api.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .and().authorizeRequests().antMatchers("/console/**").permitAll()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and().authorizeRequests().anyRequest().denyAll();
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }
}
