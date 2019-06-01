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
                .antMatchers("/v1/admin/**").authenticated()
                .antMatchers("/v1/categorias/**").authenticated()
                .antMatchers("/v1/carteiras/**").authenticated()
                .antMatchers("/v1/cartoes/**").authenticated()
                .antMatchers("/v1/lancamentos/**").authenticated()
                .antMatchers("/v1/faturas/**").authenticated()
                .and().authorizeRequests().antMatchers("/console/**").permitAll()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and().authorizeRequests().anyRequest().denyAll();
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }
}
