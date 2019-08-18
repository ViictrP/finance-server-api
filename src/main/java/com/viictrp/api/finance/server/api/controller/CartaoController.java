package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.ICartaoService;
import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.dto.CartaoDTO;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/cartoes")
@CrossOrigin("*")
public class CartaoController {

    private final ICartaoService service;
    private final IFaturaService faturaService;

    public CartaoController(ICartaoService service,
                            IFaturaService faturaService) {
        this.service = service;
        this.faturaService = faturaService;
    }

    @PostMapping
    public ResponseEntity<Mono<CartaoDTO>> salvar(@Valid @RequestBody CartaoDTO cartaoDTO) {
        return new ResponseEntity<>(service.salvar(cartaoDTO, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Flux<CartaoDTO>> buscarCartoes() {
        return ResponseEntity.ok(service.buscarCartoes(SecurityContext.getUser()));
    }

    @PostMapping("{id}/faturas")
    public ResponseEntity<Mono<FaturaDTO>> salvarFatura(@PathVariable ObjectId id,
                                                        @Valid @RequestBody FaturaDTO faturaDTO) {
        return ResponseEntity.ok(faturaService.salvar(id, faturaDTO, SecurityContext.getUser()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<CartaoDTO>> buscarCartao(@PathVariable ObjectId id) {
        return ResponseEntity.ok(service.buscarCartao(id, SecurityContext.getUser()));
    }

    @GetMapping("{id}/faturas")
    public ResponseEntity<Flux<FaturaDTO>> buscarFaturas(@PathVariable ObjectId id) {
        return ResponseEntity.ok(faturaService.buscarFaturas(id, SecurityContext.getUser()));
    }
}
