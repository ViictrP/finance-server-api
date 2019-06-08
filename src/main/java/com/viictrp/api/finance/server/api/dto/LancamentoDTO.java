package com.viictrp.api.finance.server.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

public class LancamentoDTO {

    @Getter
    @Setter
    private Long id;

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
    private FaturaDTO fatura;

    @Getter
    @Setter
    private CategoriaDTO categoria;

    @Getter
    @Setter
    private CarteiraDTO carteira;

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
