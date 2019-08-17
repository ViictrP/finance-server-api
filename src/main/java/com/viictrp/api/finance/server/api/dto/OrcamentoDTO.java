package com.viictrp.api.finance.server.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

public class OrcamentoDTO {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @NotNull(message = "O valor do orçamento é obrigatório.")
    private Double valor;

    @Getter
    @Setter
    @NotNull(message = "O mês do orçamento é obrigatório.")
    private String mes;

    @Getter
    @Setter
    private String carteiraId;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
