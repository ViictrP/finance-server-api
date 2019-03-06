package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class User extends Model<Long> {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    private String email;
}
