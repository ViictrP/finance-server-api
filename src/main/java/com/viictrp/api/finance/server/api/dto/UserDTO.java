package com.viictrp.api.finance.server.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class UserDTO {

    @Getter
    @Setter
    private Long id;

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

    @Getter
    @Setter
    private List<InvoiceDTO> invoices;

    @Getter
    @Setter
    private List<CategoryDTO> categories;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
