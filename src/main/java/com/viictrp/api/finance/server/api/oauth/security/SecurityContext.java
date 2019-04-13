package com.viictrp.api.finance.server.api.oauth.security;

import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityContext {

    private static final String USER_NOT_FOUND = "Erro ao buscar usuário logado";

    public static OAuthUser getUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof OAuthUser) {
                return (OAuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }
        }
        throw new ResourceNotFoundException(USER_NOT_FOUND);
    }
}
