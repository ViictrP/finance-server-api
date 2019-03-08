package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Invoice;

import java.util.List;

public interface IInvoiceService {

    Invoice buscarPorId(Long id, Long userId);
    List<Invoice> buscarFaturas(Long userId);
}
