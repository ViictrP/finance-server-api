package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICartaoService;
import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.fatura.FaturaConverter;
import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.fatura.FaturaRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaturaService implements IFaturaService {

    private static final String FATURA_NOT_FOUND = "A fatura não foi encontrada para o ID fornecido";

    private final FaturaRepository repository;
    private final ICartaoService cartaoService;
    private final FaturaConverter converter;

    public FaturaService(FaturaRepository repository,
                         ICartaoService cartaoService,
                         FaturaConverter converter) {
        this.repository = repository;
        this.cartaoService = cartaoService;
        this.converter = converter;
    }

    @Override
    public FaturaDTO salvar(ObjectId cartaoId, FaturaDTO faturaDTO, OAuthUser user) {
        cartaoService.buscarCartao(cartaoId, user);
        Fatura fatura = converter.toEntity(faturaDTO);
        fatura.setCartaoId(cartaoId);
        Audity.audityEntity(user, fatura);
        repository.save(fatura);
        return converter.toDto(fatura);
    }

    @Override
    public FaturaDTO buscarFatura(ObjectId id) {
        return repository.findById(id)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(FATURA_NOT_FOUND));
    }

    @Override
    public List<FaturaDTO> buscarFaturas(ObjectId idCartao, OAuthUser user) {
        return repository.findByCartaoId(idCartao)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }
}
