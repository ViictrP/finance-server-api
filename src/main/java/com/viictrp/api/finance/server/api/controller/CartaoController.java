package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.ICartaoService;
import com.viictrp.api.finance.server.api.dto.CartaoDTO;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
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

    public CartaoController(ICartaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CartaoDTO> salvar(@Valid @RequestBody CartaoDTO cartaoDTO) {
        return new ResponseEntity<>(service.salvar(cartaoDTO, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CartaoDTO>> buscarCartoes() {
        return ResponseEntity.ok(service.buscarCartoes(SecurityContext.getUser()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoDTO> buscarCartao(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarCartao(id, SecurityContext.getUser()));
    }
}
