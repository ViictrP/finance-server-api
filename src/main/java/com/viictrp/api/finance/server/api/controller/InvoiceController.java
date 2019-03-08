package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.IInvoiceService;
import com.viictrp.api.finance.server.api.domain.Invoice;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.oauth.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@CrossOrigin("*")
public class InvoiceController {

    @Autowired
    private IInvoiceService service;

    @GetMapping
    public ResponseEntity<List<Invoice>> buscarInvoices() {
        OAuthUser user = SecurityUtils.getUser();
        return ResponseEntity.ok(service.buscarFaturas(user.getUserId()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Invoice> buscarInvoice(@PathVariable("id") Long id) {
        OAuthUser user = SecurityUtils.getUser();
        return ResponseEntity.ok(service.buscarPorId(id, user.getUserId()));
    }
}
