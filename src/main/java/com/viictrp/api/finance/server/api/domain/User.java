package com.viictrp.api.finance.server.api.domain;

import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends Model<Long> {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @Transient
    private OAuthUser oAuthUser;

    @Getter
    @Setter
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice> invoices;

    @Getter
    @Setter
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> categories;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
