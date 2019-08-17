package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.business.interfaces.ILancamentoService;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/faturas")
@CrossOrigin("*")
public class FaturaController {

    private final IFaturaService service;
    private final ILancamentoService lancamentoService;

    public FaturaController(IFaturaService service, ILancamentoService lancamentoService) {
        this.service = service;
        this.lancamentoService = lancamentoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturaDTO> buscarFatura(@PathVariable ObjectId id) {
        return ResponseEntity.ok(service.buscarFatura(id));
    }

    @GetMapping("/{id}/lancamentos")
    public ResponseEntity<List<LancamentoDTO>> buscarLancamentos(@PathVariable ObjectId id) {
        return ResponseEntity.ok(lancamentoService.buscarLancamentosByFatura(id));
    }
}
