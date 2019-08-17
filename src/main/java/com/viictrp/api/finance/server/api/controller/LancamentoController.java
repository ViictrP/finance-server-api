package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.ILancamentoService;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/lancamentos")
@CrossOrigin("*")
public class LancamentoController {

    private final ILancamentoService service;

    public LancamentoController(ILancamentoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDTO> buscarLancamento(@PathVariable ObjectId id) {
        return ResponseEntity.ok(service.buscarLancamento(id));
    }
}
