package com.viictrp.api.finance.server.api.converter;

import com.viictrp.api.finance.server.api.domain.Invoice;
import com.viictrp.api.finance.server.api.dto.InvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConverter implements Converter<Invoice, InvoiceDTO> {

    private final CategoryConverter categoryConverter;

    private final UserConverter userConverter;

    public InvoiceConverter(CategoryConverter categoryConverter, UserConverter userConverter) {
        this.categoryConverter = categoryConverter;
        this.userConverter = userConverter;
    }

    @Override
    public Invoice toEntity(InvoiceDTO invoiceDTO) {
        Invoice entity = null;
        if (invoiceDTO != null) {
            entity = new Invoice();
            entity.setTitle(invoiceDTO.getTitle());
            entity.setDescription(invoiceDTO.getDescription());
            entity.setCategory(categoryConverter.toEntity(invoiceDTO.getCategory()));
        }
        return entity;
    }

    @Override
    public InvoiceDTO toDto(Invoice invoice) {
        InvoiceDTO dto = null;
        if (invoice != null) {
            dto = new InvoiceDTO();
            dto.setId(invoice.getId());
            dto.setTitle(invoice.getTitle());
            dto.setDescription(invoice.getDescription());
            dto.setPaid(invoice.getPaid());
            dto.setCategory(categoryConverter.toDto(invoice.getCategory()));
            dto.setUser(userConverter.toDto(invoice.getUser()));
        }
        return dto;
    }
}
