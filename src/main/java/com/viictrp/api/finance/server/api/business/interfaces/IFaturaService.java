package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.InvoiceDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

import java.util.List;

public interface IFaturaService {

    InvoiceDTO buscarPorId(Long id, Long userId);
    InvoiceDTO save(InvoiceDTO dto, OAuthUser user);
    InvoiceDTO atualizarInvoice(InvoiceDTO dto, OAuthUser user);
    List<InvoiceDTO> buscarFaturas(Long userId);
    void apagarInvoice(Long id, OAuthUser user);
}
