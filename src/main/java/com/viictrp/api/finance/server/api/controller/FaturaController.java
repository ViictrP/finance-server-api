package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.business.interfaces.ILancamentoService;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

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
    public ResponseEntity<Mono<FaturaDTO>> buscarFatura(@PathVariable ObjectId id) {
        return ResponseEntity.ok(service.buscarFatura(id));
    }

    @PostMapping("/{faturaId}/lancamentos")
    public ResponseEntity<Mono<LancamentoDTO>> salvarNaFatura(@PathVariable ObjectId faturaId,
                                                              @Valid @RequestBody LancamentoDTO lancamentoDTO) {
        return ResponseEntity.ok(lancamentoService.salvarNaFatura(faturaId, lancamentoDTO, SecurityContext.getUser()));
    }

    @GetMapping("/{id}/lancamentos")
    public ResponseEntity<Flux<LancamentoDTO>> buscarLancamentos(@PathVariable ObjectId id) {
        return ResponseEntity.ok(lancamentoService.buscarLancamentosDaFatura(id));
    }
}
