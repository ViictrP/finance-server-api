package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.IOrcamentoService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.common.DateUtils;
import com.viictrp.api.finance.server.api.converter.OrcamentoConverter;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Orcamento;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.OrcamentoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.CarteiraRepository;
import com.viictrp.api.finance.server.api.persistence.OrcamentoRepository;
import com.viictrp.api.finance.server.api.persistence.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrcamentoService implements IOrcamentoService {

    private final OrcamentoConverter converter;
    private final OrcamentoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CarteiraRepository carteiraRepository;

    public OrcamentoService(OrcamentoConverter converter,
                            OrcamentoRepository repository,
                            UsuarioRepository usuarioRepository,
                            CarteiraRepository carteiraRepository) {
        this.converter = converter;
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.carteiraRepository = carteiraRepository;
    }

    @Override
    @Transactional
    public OrcamentoDTO salvar(OrcamentoDTO orcamentoDTO, OAuthUser user) {
         Orcamento orcamento = repository.findByMes(MesType.customValueOf(orcamentoDTO.getMes()))
                .orElse(converter.toEntity(orcamentoDTO));
        Usuario usuario = usuarioRepository.findById(user.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        if (!orcamento.isNew()) {
            Audity.audityEntity(user, orcamento);
            orcamento.mergeDados(converter.toEntity(orcamentoDTO));
        } else {
            Carteira carteira = carteiraRepository.findByMesAndUsuario(MesType.customValueOf(orcamentoDTO.getMes()), usuario)
                    .orElse(Carteira.criar(orcamento, usuario));
            orcamento.setCarteira(carteira);
            Audity.audityEntity(user, orcamento, carteira);
            carteiraRepository.save(carteira);
        }
        repository.save(orcamento);
        return converter.toDto(orcamento);
    }

    @Override
    public OrcamentoDTO buscarOrcamento(Long carteiraID, Long orcamentoID, OAuthUser user) {
        Usuario usuario = usuarioRepository.findById(user.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para ID fornecido"));
        Carteira carteira = carteiraRepository.findByIdAndUsuario(carteiraID, usuario)
                .orElseThrow(() -> new ResourceNotFoundException("Carteira não encontrada para o ID fornecido"));
        return repository.findByCarteiraAndId(carteira, orcamentoID)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Orçamento não encontrado para o ID fornecido"));
    }
}
