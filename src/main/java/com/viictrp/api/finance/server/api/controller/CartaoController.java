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

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<CartaoDTO> salvar(@Valid @RequestBody CartaoDTO cartaoDTO) {
        return new ResponseEntity<>(service.salvar(cartaoDTO, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CartaoDTO>> buscarCartoes() {
        return ResponseEntity.ok(service.buscarCartoes(SecurityContext.getUser()));
    }

    @PostMapping("{id}/faturas")
    public ResponseEntity<FaturaDTO> salvarFatura(@PathVariable ObjectId id,
                                                  @Valid @RequestBody FaturaDTO faturaDTO) {
        return ResponseEntity.ok(faturaService.salvar(id, faturaDTO, SecurityContext.getUser()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoDTO> buscarCartao(@PathVariable ObjectId id) {
        return ResponseEntity.ok(service.buscarCartao(id, SecurityContext.getUser()));
    }

    @GetMapping("{id}/faturas")
    public ResponseEntity<List<FaturaDTO>> buscarFaturas(@PathVariable ObjectId id) {
        return ResponseEntity.ok(faturaService.buscarFaturas(id, SecurityContext.getUser()));
    }
}
