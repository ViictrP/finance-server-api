package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class Categoria extends Model {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    private ObjectId id;

    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private ObjectId usuarioId;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}


