package com.viictrp.api.finance.server.api.domain;

import com.viictrp.api.finance.server.api.domain.enums.MesType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carteira extends Model<Long> {

    @Getter
    @Setter
    @OneToOne(mappedBy = "carteira", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Orcamento orcamento;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private MesType mes;

    @Getter
    @Setter
    @OneToMany(mappedBy="carteira", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lancamento> lancamentos;

    @Getter
    @Setter
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Usuario usuario;

    public Carteira() {

    }

    public Carteira(MesType mes) {
        this.mes = mes;
    }

    public static Carteira criar(Orcamento orcamento, Usuario usuario) {
        Carteira carteira = new Carteira(orcamento.getMes());
        carteira.setUsuario(usuario);
        orcamento.setCarteira(carteira);
        return carteira;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public void addLancamento(Lancamento lancamento) {
        if (lancamento != null) {
            if (this.lancamentos == null) {
                this.lancamentos = new ArrayList<>();
            }
            lancamento.setCarteira(this);
            lancamentos.add(lancamento);
        }
    }
}
