package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@Entity
public class Lancamento extends Model<Long> {

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
    private String codigoParcela;

    @Getter
    @Setter
    @Transient
    private int quantidadeParcelas;

    @Getter
    @Setter
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Fatura fatura;

    @Getter
    @Setter
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Categoria categoria;

    @Getter
    @Setter
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Carteira carteira;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
