package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.business.interfaces.ICategoriaService;
import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.business.interfaces.ILancamentoService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.common.DateUtils;
import com.viictrp.api.finance.server.api.converter.LancamentoConverter;
import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.LancamentoRepository;
import org.bson.types.ObjectId;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LancamentoService implements ILancamentoService {

    private static final Mono<LancamentoDTO> NOT_FOUND_FALLBACK = Mono.error(new ResourceNotFoundException("O lançamento não foi encontrado para o ID fornecido"));

    private final LancamentoRepository repository;
    private final LancamentoConverter converter;
    private final IFaturaService faturaService;
    private final ICarteiraService carteiraService;
    private final ICategoriaService categoriaService;

    public LancamentoService(LancamentoRepository repository,
                             LancamentoConverter converter,
                             IFaturaService faturaService,
                             ICarteiraService carteiraService,
                             ICategoriaService categoriaService) {
        this.repository = repository;
        this.converter = converter;
        this.faturaService = faturaService;
        this.carteiraService = carteiraService;
        this.categoriaService = categoriaService;
    }

    @Override
    public Mono<LancamentoDTO> salvarNaCarteira(ObjectId carteiraId, LancamentoDTO lancamentoDTO, OAuthUser user) {
        return carteiraService.buscarCarteira(carteiraId, user)
                .flatMap(carteiraDTO -> {
                    Lancamento lancamento = converter.toEntity(lancamentoDTO);
                    ObjectId categoriaId = new ObjectId(lancamentoDTO.getCategoriaId());
                    return categoriaService.buscarPorId(categoriaId, user)
                            .flatMap(categoriaDTO -> {
                                lancamento.setCategoriaId(categoriaId);
                                lancamento.setCarteiraId(carteiraId);
                                Audity.audityEntity(user, lancamento);
                                return repository.save(lancamento)
                                        .map(converter::toDto);
                            });
                });
    }

    @Override
    public Mono<LancamentoDTO> salvarNaFatura(ObjectId faturaId, LancamentoDTO lancamentoDTO, OAuthUser user) {
        return faturaService.buscarFatura(faturaId)
                .flatMap(faturaDTO -> {
                    if (DateUtils.isTodayAfterThan(faturaDTO.getDiaFechamento())) {
                        return faturaService.buscarFaturaProximoMes(faturaDTO)
                                .flatMap(proximaFatura -> salvar(proximaFatura, lancamentoDTO, user));
                    } else {
                        return salvar(faturaDTO, lancamentoDTO, user);
                    }
                });
    }

    private Mono<LancamentoDTO> salvar(FaturaDTO faturaDTO, LancamentoDTO lancamentoDTO, OAuthUser user) {
        Lancamento lancamento = converter.toEntity(lancamentoDTO);
        ObjectId categoriaId = new ObjectId(lancamentoDTO.getCategoriaId());
        return categoriaService.buscarPorId(categoriaId, user)
                .flatMap(categoriaDTO -> {
                    lancamento.setCategoriaId(categoriaId);
                    lancamento.setFaturaId(new ObjectId(faturaDTO.getId()));
                    Audity.audityEntity(user, lancamento);
                    if (lancamentoDTO.getIsParcela()) {
                        criarParcelas(faturaDTO, lancamentoDTO, user);
                    }
                    return repository.save(lancamento)
                            .map(converter::toDto);
                });
    }

    @Async
    @Override
    public void criarParcelas(FaturaDTO faturaDTO, LancamentoDTO lancamentoDTO, OAuthUser user) {
        for (int i = 0; i < lancamentoDTO.getQuantidadeParcelas(); i++) {
            faturaService.buscarFaturaProximoMes(faturaDTO)
                    .subscribe(proximaFaturaDTO -> {
                        Lancamento lancamento = new Lancamento(lancamentoDTO);
                        lancamento.setFaturaId(new ObjectId(proximaFaturaDTO.getId()));
                        Audity.audityEntity(user, lancamento);
                        repository.save(lancamento);
                    });
        }
    }

    @Override
    public Mono<LancamentoDTO> buscarLancamento(ObjectId id) {
        return repository.findById(id)
                .map(converter::toDto)
                .switchIfEmpty(NOT_FOUND_FALLBACK);
    }

    @Override
    public Flux<LancamentoDTO> buscarLancamentosDaFatura(ObjectId idFatura) {
        return repository.findByFaturaId(idFatura)
                .map(converter::toDto);
    }

    @Override
    public Flux<LancamentoDTO> buscarLancamentosDaCarteira(ObjectId idCarteira) {
        return repository.findByCarteiraId(idCarteira)
                .map(converter::toDto);
    }

}
