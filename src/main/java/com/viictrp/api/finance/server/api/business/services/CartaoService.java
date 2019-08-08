package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICartaoService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.cartao.CartaoConverter;
import com.viictrp.api.finance.server.api.domain.Cartao;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.dto.CartaoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaoService implements ICartaoService {

    private final CartaoRepository repository;
    private final UsuarioService usuarioService;
    private final CartaoConverter converter;

    public CartaoService(CartaoRepository repository, UsuarioService usuarioService, CartaoConverter converter) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.converter = converter;
    }

    @Override
    public CartaoDTO salvar(CartaoDTO cartaoDTO, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        Cartao cartao = converter.toEntity(cartaoDTO);
        cartao.setUsuario(usuario);
        Audity.audityEntity(user, cartao);
        repository.save(cartao);
        return converter.toDto(cartao);
    }

    @Override
    public Cartao buscarCartao(Long id, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado para ID fornecido"));
    }

    @Override
    public List<CartaoDTO> buscarCartoes(OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByUsuario(usuario)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }
}
