package com.viictrp.api.finance.server.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class CartaoDTO {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private LocalDate dataFechamento;

    @Getter
    @Setter
    private Double limite;

    @Getter
    @Setter
    private String usuarioId;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
