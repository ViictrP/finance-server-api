package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.InvoiceDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

import java.util.List;

public interface IInvoiceService {

    InvoiceDTO buscarPorId(Long id, Long userId);
    InvoiceDTO save(InvoiceDTO dto, OAuthUser user);
    List<InvoiceDTO> buscarFaturas(Long userId);
}
