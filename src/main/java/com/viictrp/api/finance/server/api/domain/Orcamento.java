package com.viictrp.api.finance.server.api.domain;

import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Document
public class Orcamento extends Model {

    @Getter
    @Setter
    private ObjectId id;

    @Getter
    @Setter
    private Double valor;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private MesType mes;

    @Getter
    @Setter
    private ObjectId carteiraId;

    public void mergeDados(Orcamento orcamento, OAuthUser user) {
        this.valor = orcamento.getValor();
        Audity.audityEntity(user, this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
