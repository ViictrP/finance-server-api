package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.IInvoiceService;
import com.viictrp.api.finance.server.api.converter.InvoiceConverter;
import com.viictrp.api.finance.server.api.domain.Invoice;
import com.viictrp.api.finance.server.api.dto.InvoiceDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.persistence.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService implements IInvoiceService {

    private final IInvoiceRepository repository;

    private final InvoiceConverter converter;

    public InvoiceService(IInvoiceRepository repository, InvoiceConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public InvoiceDTO buscarPorId(Long id, Long userId) {
        return repository.findByIdAndUserId(id, userId)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma fatura encontrada"));
    }

    @Override
    public List<InvoiceDTO> buscarFaturas(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }
}
