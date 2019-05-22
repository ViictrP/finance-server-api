package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.IOrcamentoService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.OrcamentoConverter;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Orcamento;
import com.viictrp.api.finance.server.api.domain.Usuario;
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
    public OrcamentoDTO salvarOrcamento(OrcamentoDTO orcamentoDTO, OAuthUser user) {
        Orcamento orcamento = converter.toEntity(orcamentoDTO);
        Usuario usuario = usuarioRepository.findById(user.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        Carteira carteira = orcamento.criarCateira(orcamento, usuario);
        Audity.audityEntity(user, orcamento, carteira);
        carteiraRepository.save(carteira);
        orcamento.setCarteira(carteira);
        repository.save(orcamento);
        return converter.toDto(orcamento);
    }
}
