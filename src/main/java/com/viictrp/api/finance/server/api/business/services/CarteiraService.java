package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.business.interfaces.IUsuarioService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.carteira.CarteiraConverter;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Orcamento;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.carteira.CarteiraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteiraService implements ICarteiraService {

    private static final String CARTEIRA_NOT_FOUND = "Carteira não encontrada pra o ID fornecido";

    private final CarteiraRepository repository;
    private final IUsuarioService usuarioService;
    private final CarteiraConverter converter;

    public CarteiraService(CarteiraRepository repository,
                           IUsuarioService usuarioService,
                           CarteiraConverter converter) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.converter = converter;
    }

    @Override
    public CarteiraDTO salvar(CarteiraDTO carteiraDTO) {
        Carteira carteira = converter.toEntity(carteiraDTO);
        repository.save(carteira);
        return converter.toDto(carteira);
    }

    @Override
    public CarteiraDTO buscarCarteira(Long id, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByIdAndUsuario(id, usuario)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(CARTEIRA_NOT_FOUND));
    }

    @Override
    public Carteira buscarCarteira(Long id, Usuario usuario) {
        return repository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new ResourceNotFoundException(CARTEIRA_NOT_FOUND));
    }

    @Override
    public List<Carteira> buscarPorUsuario(OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByUsuario(usuario);
    }

    @Override
    public void salvarOrcamentoNaCarteira(MesType mes, OAuthUser user, Orcamento orcamento) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        Carteira carteira = repository.findByMesAndUsuario(mes, usuario)
                .orElse(Carteira.criar(orcamento, usuario));
        orcamento.setCarteira(carteira);
        Audity.audityEntity(user, orcamento, carteira);
        repository.save(carteira);
    }
}
