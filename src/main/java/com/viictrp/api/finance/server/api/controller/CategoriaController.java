package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.ICategoriaService;
import com.viictrp.api.finance.server.api.dto.CategoriaDTO;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
@CrossOrigin("*")
public class CategoriaController {

    private final ICategoriaService service;

    public CategoriaController(ICategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> saveCategory(@Valid @RequestBody CategoriaDTO dto) {
        return new ResponseEntity<>(this.service.save(dto, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findCategorias() {
        return ResponseEntity.ok(service.buscarCategorias(SecurityContext.getUser()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id, SecurityContext.getUser()));
    }
}
