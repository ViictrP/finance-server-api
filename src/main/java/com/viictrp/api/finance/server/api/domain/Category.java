package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;

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
}
