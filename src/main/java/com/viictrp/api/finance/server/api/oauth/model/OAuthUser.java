package com.viictrp.api.finance.server.api.oauth.model;

import com.viictrp.api.finance.server.api.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;

@Document
public class OAuthUser implements UserDetails {

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private String fullname;

    @Setter
    private String username;

    @Setter
    private String password;

    @Getter
    @Setter
    private Boolean master = Boolean.FALSE;

    private Collection<GrantedAuthority> authorities;

    @Setter
    private Boolean enabled = Boolean.TRUE;

    @Setter
    private Boolean accountNonLocked;

    public OAuthUser() {
        this.authorities = new ArrayList<>();
    }

    public void setUser(User user) {
        this.userId = user.getId();
        this.fullname = user.getName() + " " + user.getLastname();
        this.username = user.getEmail();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (this.authorities == null) {
            this.authorities = new ArrayList<>();
        }
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (accountNonLocked == null) {
            throw new IllegalStateException("Informações sobre a situação da conta bloqueada não encontrado.");
        }
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
