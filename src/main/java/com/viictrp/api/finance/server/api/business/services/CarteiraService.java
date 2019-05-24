package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.converter.CarteiraConverter;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.CarteiraRepository;
import com.viictrp.api.finance.server.api.persistence.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarteiraService implements ICarteiraService {

    private final CarteiraRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CarteiraConverter converter;

    public CarteiraService(CarteiraRepository repository,
                           UsuarioRepository usuarioRepository,
                           CarteiraConverter converter) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
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
        Usuario usuario = buscarUsuario(user.getUsuarioId());
        return repository.findByIdAndUsuario(id, usuario)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Carteira não encontrada pra o ID fornecido"));
    }

    @Override
    public List<Carteira> buscarPorUsuario(OAuthUser user) {
        Usuario usuario = buscarUsuario(user.getUsuarioId());
        return repository.findByUsuario(usuario);
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado para o ID fornecido"));
    }
}
