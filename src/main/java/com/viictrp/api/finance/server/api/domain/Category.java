package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category extends Model<Long> {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @OneToMany(mappedBy="category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice> invoices;

    @Getter
    @Setter
    @ManyToOne
    @PrimaryKeyJoinColumn
    private User user;

    @Getter
    @Setter
    private Boolean excluido = Boolean.FALSE;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public void addInvoice(Invoice invoice) {
        if (invoice != null) {
            this.invoices.add(invoice);
            invoice.setCategory(this);
        }
    }
}
