package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.ICategoriaService;
import com.viictrp.api.finance.server.api.dto.CategoriaDTO;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/categorias")
@CrossOrigin("*")
public class CategoriaController {

    private final ICategoriaService service;

    public CategoriaController(ICategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Mono<CategoriaDTO>> saveCategory(@Valid @RequestBody CategoriaDTO dto) {
        return new ResponseEntity<>(this.service.save(dto, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Flux<CategoriaDTO>> findCategorias() {
        return ResponseEntity.ok(service.buscarCategorias(SecurityContext.getUser()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<CategoriaDTO>> findCategoria(@PathVariable ObjectId id) {
        return ResponseEntity.ok(service.buscarPorId(id, SecurityContext.getUser()));
    }
}
