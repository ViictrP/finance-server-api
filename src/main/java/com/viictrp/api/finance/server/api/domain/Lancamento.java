package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;

@Document
public class Lancamento extends Model {

    @Getter
    @Setter
    @Id
    private ObjectId id;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private Double valor;

    @Getter
    @Setter
    private DateTime data;

    @Getter
    @Setter
    private String codigoParcela;

    @Getter
    @Setter
    @Transient
    private int quantidadeParcelas;

    @Getter
    @Setter
    private ObjectId faturaId;

    @Getter
    @Setter
    private ObjectId categoriaId;

    @Getter
    @Setter
    private ObjectId carteiraId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
