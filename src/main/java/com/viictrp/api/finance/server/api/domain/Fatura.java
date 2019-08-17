package com.viictrp.api.finance.server.api.domain;

import com.viictrp.api.finance.server.api.domain.enums.MesType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Document
public class Fatura extends Model {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private ObjectId id;

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
    private ObjectId cartaoId;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private MesType mes;

    @Getter
    @Setter
    private Integer diaFechamento;

    public void mergeDados(@NotNull Fatura newFatura) {
        this.titulo = newFatura.getTitulo();
        this.pago = newFatura.getPago();
        this.descricao = newFatura.getDescricao();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
