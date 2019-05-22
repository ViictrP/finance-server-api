package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Cartao extends Model<Long> {

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
    @OneToMany(mappedBy="cartao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Fatura> faturas;

    @Getter
    @Setter
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Usuario usuario;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
