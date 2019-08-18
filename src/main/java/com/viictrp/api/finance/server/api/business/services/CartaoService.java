package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICartaoService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.common.PreconditionsRest;
import com.viictrp.api.finance.server.api.converter.CartaoConverter;
import com.viictrp.api.finance.server.api.domain.Cartao;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.dto.CartaoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.CartaoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CartaoService implements ICartaoService {

    private static final Mono<CartaoDTO> NOT_FOUND_FALLBACK = Mono.error(new ResourceNotFoundException("Cartão não encontrado para ID fornecido"));

    private final CartaoRepository repository;
    private final UsuarioService usuarioService;
    private final CartaoConverter converter;

    public CartaoService(CartaoRepository repository, UsuarioService usuarioService, CartaoConverter converter) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.converter = converter;
    }

    @Override
    public Mono<CartaoDTO> salvar(CartaoDTO cartaoDTO, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        Cartao cartao = converter.toEntity(cartaoDTO);
        cartao.setUsuarioId(usuario.getId());
        Audity.audityEntity(user, cartao);
        return repository.save(cartao).map(converter::toDto);
    }

    @Override
    public Mono<CartaoDTO> buscarCartao(ObjectId id, OAuthUser user) {
        PreconditionsRest.checkCondition(id != null, "Por favor informe o cartão");
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByIdAndUsuarioId(id, usuario.getId())
                .map(converter::toDto)
                .switchIfEmpty(NOT_FOUND_FALLBACK);
    }

    @Override
    public Flux<CartaoDTO> buscarCartoes(OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByUsuarioId(usuario.getId())
                .map(converter::toDto);
    }
}
