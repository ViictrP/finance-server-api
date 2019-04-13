package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.InvoiceDTO;

import java.util.List;

public interface IInvoiceService {

    InvoiceDTO buscarPorId(Long id, Long userId);
    List<InvoiceDTO> buscarFaturas(Long userId);
}
