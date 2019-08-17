package com.viictrp.api.finance.server.api.domain;

import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;

@Document
public class Usuario extends Model {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    private ObjectId id;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
