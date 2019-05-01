package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
public class Invoice extends Model<Long> {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Boolean paid = Boolean.FALSE;

    @Getter
    @Setter
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Category category;

    @Getter
    @Setter
    private Boolean excluido = Boolean.FALSE;

    @Getter
    @Setter
    @ManyToOne
    @PrimaryKeyJoinColumn
    private User user;

    public void mergeDados(@NotNull Invoice newInvoice) {
        this.title = newInvoice.getTitle();
        this.paid = newInvoice.getPaid();
        this.description = newInvoice.getDescription();
        if (!this.getCategory().getId().equals(newInvoice.getCategory().getId())) {
            this.category = newInvoice.getCategory();
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
