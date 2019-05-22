package com.viictrp.api.finance.server.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.List;

public class CartaoDTO {

    @Getter
    @Setter
    private Long id;

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
    private List<FaturaDTO> faturas;

    @Getter
    @Setter
    private UsuarioDTO usuario;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}