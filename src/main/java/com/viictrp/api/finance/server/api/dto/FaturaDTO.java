package com.viictrp.api.finance.server.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FaturaDTO {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private Boolean pago = Boolean.FALSE;

    @Getter
    @Setter
    private String mes;

    @Getter
    @Setter
    private Integer diaFechamento;

    @Getter
    @Setter
    private String cartaoId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
