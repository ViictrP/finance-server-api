package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.business.interfaces.IUsuarioService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.CarteiraConverter;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.CarteiraRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarteiraService implements ICarteiraService {

    private static final String CARTEIRA_NOT_FOUND = "Carteira não encontrada pra o ID fornecido";
    private static final Mono<CarteiraDTO> NOT_FOUND_FALLBACK = Mono.error(new ResourceNotFoundException(CARTEIRA_NOT_FOUND));

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
    public Mono<CarteiraDTO> salvar(CarteiraDTO carteiraDTO, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        Carteira carteira = converter.toEntity(carteiraDTO);
        carteira.setUsuarioId(usuario.getId());
        Audity.audityEntity(user, carteira);
        return repository.save(carteira).map(converter::toDto);
    }

    @Override
    public Mono<CarteiraDTO> buscarCarteira(ObjectId id, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByIdAndUsuarioId(id, usuario.getId())
                .map(converter::toDto)
                .switchIfEmpty(NOT_FOUND_FALLBACK);
    }

    @Override
    public Flux<CarteiraDTO> buscarPorUsuario(OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByUsuarioId(usuario.getId()).map(converter::toDto);
    }
}
