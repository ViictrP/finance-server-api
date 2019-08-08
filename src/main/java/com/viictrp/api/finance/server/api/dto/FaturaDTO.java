package com.viictrp.api.finance.server.api.dto;

import com.viictrp.api.finance.server.api.domain.Cartao;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.LocalDate;

import java.util.List;

public class FaturaDTO {

    @Getter
    @Setter
    private Long id;

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
    private CartaoDTO cartao;

    @Getter
    @Setter
    private List<LancamentoDTO> lancamentos;

    @Getter
    @Setter
    private String mes;

    @Getter
    @Setter
    private Integer diaFechamento;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
