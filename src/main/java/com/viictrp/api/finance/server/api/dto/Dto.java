package com.viictrp.api.finance.server.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

public class Dto {

    @Getter
    @Setter
    private DateTime createdDate;

    @Getter
    @Setter
    private String createdBy;

    @Getter
    @Setter
    private String lastModifiedBy;

    @Getter
    @Setter
    private DateTime lastModifiedDate;
}
