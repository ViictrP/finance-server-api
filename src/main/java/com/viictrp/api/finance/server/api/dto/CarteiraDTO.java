package com.viictrp.api.finance.server.api.dto;

import com.viictrp.api.finance.server.api.domain.enums.MesType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CarteiraDTO extends Dto {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private MesType mes;

    @Getter
    @Setter
    private String usuarioId;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
