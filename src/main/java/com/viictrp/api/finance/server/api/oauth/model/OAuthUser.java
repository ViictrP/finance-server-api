package com.viictrp.api.finance.server.api.oauth.model;

import com.viictrp.api.finance.server.api.domain.Model;
import com.viictrp.api.finance.server.api.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayDeque;
import java.util.Collection;

@Entity
public class OAuthUser extends Model<Long> implements UserDetails {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @Setter
    private String password;

    @Getter
    @Setter
    private Boolean master = Boolean.FALSE;

    @Transient
    private Collection<GrantedAuthority> authorities;

    @Setter
    private Boolean enabled = Boolean.TRUE;

    @Setter
    private Boolean accountNonLocked;

    public OAuthUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (this.authorities == null) {
            this.authorities = new ArrayDeque<>();
        }
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
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
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
