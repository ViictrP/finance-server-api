package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.IInvoiceService;
import com.viictrp.api.finance.server.api.domain.Invoice;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.persistence.interfaces.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private IInvoiceRepository repository;

    @Override
    public Invoice buscarPorId(Long id, Long userId) {
        return repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma fatura encontrada"));
    }

    @Override
    public List<Invoice> buscarFaturas(Long userId) {
        return repository.findByUserId(userId);
    }
}
