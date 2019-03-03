package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
public class Model<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 5442167362566615887L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private T id;

    @CreatedDate
    @Getter
    @Setter
    private LocalDate createDate;

    @LastModifiedDate
    @Getter
    @Setter
    private LocalDate lastUpdateDate;

    @CreatedBy
    @Getter
    @Setter
    private String createdBy;

    @LastModifiedBy
    @Getter
    @Setter
    private String lastModifiedBy;

    public Boolean isNew() {
        return this.id == null;
    }

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDate.now();
        this.lastUpdateDate = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdateDate = LocalDate.now();
    }
}
