package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Fatura extends Model<Long> {

    private static final long serialVersionUID = 1L;

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
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Cartao cartao;

    @Getter
    @Setter
    @OneToMany(mappedBy="fatura", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lancamento> lancamentos;

    @Getter
    @Setter
    private LocalDate dataVencimento;

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
