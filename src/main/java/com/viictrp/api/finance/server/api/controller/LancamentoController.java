package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.ILancamentoService;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/lancamentos")
@CrossOrigin("*")
public class LancamentoController {

    private final ILancamentoService service;

    public LancamentoController(ILancamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LancamentoDTO> salvar(@Valid @RequestBody LancamentoDTO dto) {
        return new ResponseEntity<>(this.service.salvar(dto, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDTO> buscarLancamento(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarLancamento(id));
    }
}
