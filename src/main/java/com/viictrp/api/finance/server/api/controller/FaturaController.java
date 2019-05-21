package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.dto.InvoiceDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoices")
@CrossOrigin("*")
public class FaturaController {

    private final IFaturaService service;

    @Autowired
    public FaturaController(IFaturaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> saveInvoice(@Valid @RequestBody InvoiceDTO dto) {
        return new ResponseEntity<>(service.save(dto, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> buscarInvoices() {
        OAuthUser user = SecurityContext.getUser();
        return ResponseEntity.ok(service.buscarFaturas(user.getUsuarioId()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<InvoiceDTO> buscarInvoice(@PathVariable("id") Long id) {
        OAuthUser user = SecurityContext.getUser();
        return ResponseEntity.ok(service.buscarPorId(id, user.getUsuarioId()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<InvoiceDTO> atualizarInvoice(@PathVariable("id") Long id, @Valid @RequestBody InvoiceDTO dto) {
        OAuthUser user = SecurityContext.getUser();
        return ResponseEntity.ok(service.atualizarInvoice(dto, user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> apagarInvoice(@PathVariable("id") Long id) {
        service.apagarInvoice(id, SecurityContext.getUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}