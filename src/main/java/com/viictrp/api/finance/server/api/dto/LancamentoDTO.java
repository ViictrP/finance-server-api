package com.viictrp.api.finance.server.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;

public class LancamentoDTO extends Dto {

    @Getter
    @Setter
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
    private String faturaId;

    @Getter
    @Setter
    private String categoriaId;

    @Getter
    @Setter
    private String carteiraId;

    @Getter
    @Setter
    private Boolean isParcela;

    @Getter
    @Setter
    private Integer quantidadeParcelas;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
