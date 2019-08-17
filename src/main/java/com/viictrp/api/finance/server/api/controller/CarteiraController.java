package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.business.interfaces.ILancamentoService;
import com.viictrp.api.finance.server.api.business.interfaces.IOrcamentoService;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import org.bson.types.ObjectId;
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
    private final ILancamentoService lancamentoService;

    public CarteiraController(IOrcamentoService orcamentoService,
                              ICarteiraService service,
                              ILancamentoService lancamentoService) {
        this.orcamentoService = orcamentoService;
        this.service = service;
        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    public ResponseEntity<CarteiraDTO> salvar(@Valid @RequestBody CarteiraDTO carteiraDTO) {
        return new ResponseEntity<>(service.salvar(carteiraDTO, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Carteira>> buscarCarteiras() {
        return ResponseEntity.ok(service.buscarPorUsuario(SecurityContext.getUser()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteiraDTO> buscarCarteira(@PathVariable ObjectId id) {
        return ResponseEntity.ok(service.buscarCarteira(id, SecurityContext.getUser()));
    }

    @PostMapping("/{carteiraId}/orcamentos")
    public ResponseEntity<OrcamentoDTO> salvarOrcamento(@Valid @RequestBody OrcamentoDTO orcamentoDTO,
                                                        @PathVariable ObjectId carteiraId) {
        return new ResponseEntity<>(orcamentoService.salvar(carteiraId, orcamentoDTO, SecurityContext.getUser()), HttpStatus.CREATED);
    }

    @GetMapping("/{carteiraID}/orcamentos/{orcamentoID}")
    public ResponseEntity<OrcamentoDTO> buscarOrcamento(@PathVariable ObjectId carteiraID,
                                                        @PathVariable ObjectId orcamentoID) {
        return ResponseEntity.ok(orcamentoService.buscarOrcamento(carteiraID, orcamentoID, SecurityContext.getUser()));
    }

    @GetMapping("/{carteiraID}/lancamentos")
    public ResponseEntity<List<LancamentoDTO>> buscarLancamentos(@PathVariable ObjectId carteiraID) {
        return ResponseEntity.ok(lancamentoService.buscarLancamentosByCarteira(carteiraID, SecurityContext.getUser()));
    }
}
