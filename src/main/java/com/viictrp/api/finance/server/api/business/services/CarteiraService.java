package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.business.interfaces.IUsuarioService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.carteira.CarteiraConverter;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.carteira.CarteiraRepository;
import org.bson.types.ObjectId;
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
    public CarteiraDTO salvar(CarteiraDTO carteiraDTO, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        Carteira carteira = converter.toEntity(carteiraDTO);
        carteira.setUsuarioId(usuario.getId());
        Audity.audityEntity(user, carteira);
        repository.save(carteira);
        return converter.toDto(carteira);
    }

    @Override
    public CarteiraDTO buscarCarteira(ObjectId id, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByIdAndUsuarioId(id, usuario.getId())
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(CARTEIRA_NOT_FOUND));
    }

    @Override
    public Carteira buscarCarteira(ObjectId id, Usuario usuario) {
        return repository.findByIdAndUsuarioId(id, usuario.getId())
                .orElseThrow(() -> new ResourceNotFoundException(CARTEIRA_NOT_FOUND));
    }

    @Override
    public List<Carteira> buscarPorUsuario(OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByUsuarioId(usuario.getId());
    }
}
