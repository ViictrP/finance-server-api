package com.viictrp.api.finance.server.api.domain;

import com.viictrp.api.finance.server.api.domain.enums.MesType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Document
public class Carteira extends Model {

    @Getter
    @Setter
    private ObjectId id;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private MesType mes;

    @Getter
    @Setter
    private ObjectId usuarioId;

    public Carteira() {

    }

    public Carteira(MesType mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
