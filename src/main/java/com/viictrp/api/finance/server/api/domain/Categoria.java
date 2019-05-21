package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria extends Model<Long> {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    @OneToMany(mappedBy="categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Fatura> faturas;

    @Getter
    @Setter
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Usuario usuario;

    @Getter
    @Setter
    private Boolean excluido = Boolean.FALSE;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public void addInvoice(Fatura fatura) {
        if (fatura != null) {
            this.faturas.add(fatura);
            fatura.setCategoria(this);
        }
    }
}
