package com.viictrp.api.finance.server.api.dto;

import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CarteiraDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private OrcamentoDTO orcamento;

    @Getter
    @Setter
    private MesType mes;

    @Getter
    @Setter
    private Usuario usuario;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
