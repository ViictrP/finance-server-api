package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.ICartaoService;
import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.converter.cartao.CartaoConverter;
import com.viictrp.api.finance.server.api.dto.CartaoDTO;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
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
    private final CartaoConverter converter;
    private final IFaturaService faturaService;

    public CartaoController(ICartaoService service,
                            CartaoConverter converter,
                            IFaturaService faturaService) {
        this.service = service;
        this.converter = converter;
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

    @GetMapping("/{id}")
    public ResponseEntity<CartaoDTO> buscarCartao(@PathVariable Long id) {
        CartaoDTO cartaoDTO = converter.toDto(service.buscarCartao(id, SecurityContext.getUser()));
        return ResponseEntity.ok(cartaoDTO);
    }

    @GetMapping("{id}/faturas")
    public ResponseEntity<List<FaturaDTO>> buscarFaturas(@PathVariable Long id) {
        return ResponseEntity.ok(faturaService.buscarFaturas(id, SecurityContext.getUser()));
    }
}
