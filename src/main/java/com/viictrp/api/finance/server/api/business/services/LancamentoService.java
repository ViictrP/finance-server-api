package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICartaoService;
import com.viictrp.api.finance.server.api.business.interfaces.ILancamentoService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.common.PreconditionsRest;
import com.viictrp.api.finance.server.api.converter.lancamento.LancamentoConverter;
import com.viictrp.api.finance.server.api.domain.Cartao;
import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.lancamento.LancamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LancamentoService implements ILancamentoService {

    private final LancamentoRepository repository;
    private final LancamentoConverter converter;
    private final ICartaoService cartaoService;

    public LancamentoService(LancamentoRepository repository,
                             LancamentoConverter converter,
                             ICartaoService cartaoService) {
        this.repository = repository;
        this.converter = converter;
        this.cartaoService = cartaoService;
    }

    @Override
    public LancamentoDTO salvar(LancamentoDTO lancamento, OAuthUser user) {
        PreconditionsRest.checkCondition(lancamento.getCartao().getId() != null, "Por favor informe o código do Cartão");
        Cartao cartao = cartaoService.buscarCartao(lancamento.getCartao().getId(), user);
        Lancamento entity = converter.toEntity(lancamento);
        entity.setCartao(cartao);
        Audity.audityEntity(user, entity);
        repository.save(entity);
        return converter.toDto(entity);
    }

    @Override
    public LancamentoDTO buscarLancamento(Long id, OAuthUser user) {
        return repository.findById(id)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Override
    public List<LancamentoDTO> buscarLancamentos(Long idCartao, OAuthUser user) {
        Cartao cartao = cartaoService.buscarCartao(idCartao, user);
        return repository.findByCartao(cartao)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }
}
