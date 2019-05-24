package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.business.interfaces.IOrcamentoService;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/carteiras")
@CrossOrigin("*")
public class CarteiraController {

    private final IOrcamentoService orcamentoService;
    private final ICarteiraService service;

    public CarteiraController(IOrcamentoService orcamentoService, ICarteiraService service) {
        this.orcamentoService = orcamentoService;
        this.service = service;
    }

    @PostMapping("/orcamentos")
    public ResponseEntity<OrcamentoDTO> salvarOrcamento(@Valid @RequestBody OrcamentoDTO orcamentoDTO) {
        return new ResponseEntity<>(orcamentoService.salvar(orcamentoDTO, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<CarteiraDTO> salvar(@Valid @RequestBody CarteiraDTO carteiraDTO) {
        return new ResponseEntity<>(service.salvar(carteiraDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Carteira>> buscarCarteiras() {
        return ResponseEntity.ok(service.buscarPorUsuario(SecurityContext.getUser()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteiraDTO> buscarCarteira(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarCarteira(id, SecurityContext.getUser()));
    }

    @GetMapping("/{carteiraID}/orcamentos/{orcamentoID}")
    public ResponseEntity<OrcamentoDTO> buscarOrcamento(@PathVariable Long carteiraID, @PathVariable Long orcamentoID) {
        return ResponseEntity.ok(orcamentoService.buscarOrcamento(carteiraID, orcamentoID, SecurityContext.getUser()));
    }
}
