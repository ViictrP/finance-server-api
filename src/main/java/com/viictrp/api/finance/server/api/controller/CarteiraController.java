package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.IOrcamentoService;
import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/carteiras")
@CrossOrigin("*")
public class CarteiraController {

    private final IOrcamentoService service;

    public CarteiraController(IOrcamentoService service) {
        this.service = service;
    }

    @PostMapping("/orcamentos")
    public ResponseEntity<OrcamentoDTO> salvarCarteira(@Valid @RequestBody OrcamentoDTO orcamentoDTO) {
        return new ResponseEntity<>(service.salvarOrcamento(orcamentoDTO, SecurityContext.getUser()), HttpStatus.CREATED);
    }
}
