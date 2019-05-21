package com.viictrp.api.finance.server.api.converter;

import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.dto.InvoiceDTO;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConverter implements Converter<Fatura, InvoiceDTO> {

    private final CategoryConverter categoryConverter;

    private final UserConverter userConverter;

    public InvoiceConverter(CategoryConverter categoryConverter, UserConverter userConverter) {
        this.categoryConverter = categoryConverter;
        this.userConverter = userConverter;
    }

    @Override
    public Fatura toEntity(InvoiceDTO invoiceDTO) {
        Fatura entity = null;
        if (invoiceDTO != null) {
            entity = new Fatura();
            entity.setTitulo(invoiceDTO.getTitle());
            entity.setDescricao(invoiceDTO.getDescription());
            entity.setCategoria(categoryConverter.toEntity(invoiceDTO.getCategory()));
        }
        return entity;
    }

    @Override
    public InvoiceDTO toDto(Fatura fatura) {
        InvoiceDTO dto = null;
        if (fatura != null) {
            dto = new InvoiceDTO();
            dto.setId(fatura.getId());
            dto.setTitle(fatura.getTitulo());
            dto.setDescription(fatura.getDescricao());
            dto.setPaid(fatura.getPago());
            dto.setCategory(categoryConverter.toDto(fatura.getCategoria()));
            dto.setUser(userConverter.toDto(fatura.getUsuario()));
            dto.setExcluido(fatura.getExcluido());
        }
        return dto;
    }
}
