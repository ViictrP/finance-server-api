package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.business.interfaces.IOrcamentoService;
import com.viictrp.api.finance.server.api.business.interfaces.IUsuarioService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.common.PreconditionsRest;
import com.viictrp.api.finance.server.api.converter.orcamento.OrcamentoConverter;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Orcamento;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.orcamento.OrcamentoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService implements IOrcamentoService {

    private final OrcamentoConverter converter;
    private final OrcamentoRepository repository;
    private final IUsuarioService usuarioService;
    private final ICarteiraService carteiraService;

    public OrcamentoService(OrcamentoConverter converter,
                            OrcamentoRepository repository,
                            IUsuarioService usuarioService,
                            ICarteiraService carteiraService) {
        this.converter = converter;
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.carteiraService = carteiraService;
    }

    @Override
    public OrcamentoDTO salvar(ObjectId carteiraId, OrcamentoDTO orcamentoDTO, OAuthUser user) {
        carteiraService.buscarCarteira(carteiraId, user);
        Orcamento orcamento = repository.findByMes(MesType.customValueOf(orcamentoDTO.getMes()))
                .orElse(converter.toEntity(orcamentoDTO));
        orcamento.setCarteiraId(carteiraId);
        orcamento.setValor(orcamentoDTO.getValor());
        Audity.audityEntity(user, orcamento);
        repository.save(orcamento);
        return converter.toDto(orcamento);
    }

    @Override
    public OrcamentoDTO buscarOrcamento(ObjectId carteiraID, ObjectId orcamentoID, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        Carteira carteira = carteiraService.buscarCarteira(carteiraID, usuario);
        return repository.findByCarteiraIdAndId(carteira.getId(), orcamentoID)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Orçamento não encontrado para o ID fornecido"));
    }

}
