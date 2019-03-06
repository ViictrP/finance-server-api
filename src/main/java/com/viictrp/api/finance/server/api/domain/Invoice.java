package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

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
    @ManyToOne
    @PrimaryKeyJoinColumn
    private User user;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
