package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Document
public class Cartao extends Model {

    @Getter
    @Setter
    @Id
    private ObjectId id;

    @Getter
    @Setter
    private ObjectId usuarioId;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private LocalDate dataFechamento;

    @Getter
    @Setter
    private Double limite;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
