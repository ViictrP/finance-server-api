package com.viictrp.api.finance.server.api.domain;

import com.viictrp.api.finance.server.api.domain.enums.MesType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@Entity
public class Orcamento extends Model<Long> {

    @Getter
    @Setter
    private Double valor;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private MesType mes;

    @Getter
    @Setter
    @OneToOne
    @MapsId
    private Carteira carteira;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
