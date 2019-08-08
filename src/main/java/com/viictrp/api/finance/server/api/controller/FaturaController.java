package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.business.interfaces.ILancamentoService;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<FaturaDTO> salvar(@Valid @RequestBody FaturaDTO dto) {
        return new ResponseEntity<>(service.salvar(dto, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturaDTO> buscarFatura(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarFatura(id));
    }

    @GetMapping("/{id}/lancamentos")
    public ResponseEntity<List<LancamentoDTO>> buscarLancamentos(@PathVariable Long id) {
        return ResponseEntity.ok(lancamentoService.buscarLancamentosByFatura(id));
    }
}
