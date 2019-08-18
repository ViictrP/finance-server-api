package com.viictrp.api.finance.server.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

public class Model implements Serializable {

    private static final long serialVersionUID = 5442167362566615887L;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Getter
    @Setter
    private DateTime createDate;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Getter
    @Setter
    private DateTime lastModifiedDate;

    @CreatedBy
    @Getter
    @Setter
    private String createdBy;

    @LastModifiedBy
    @Getter
    @Setter
    private String lastModifiedBy;
}