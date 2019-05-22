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

    private final IOrcamentoService orcamentoService;

    public CarteiraController(IOrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    @PostMapping("/orcamentos")
    public ResponseEntity<OrcamentoDTO> salvarCarteira(@Valid @RequestBody OrcamentoDTO orcamentoDTO) {
        return new ResponseEntity<>(orcamentoService.salvar(orcamentoDTO, SecurityContext.getUser()), HttpStatus.CREATED);
    }
}
