package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILancamentoService {

    Mono<LancamentoDTO> salvarNaCarteira(ObjectId carteiraId, LancamentoDTO lancamento, OAuthUser user);

    Mono<LancamentoDTO> salvarNaFatura(ObjectId faturaId, LancamentoDTO lancamento, OAuthUser user);

    void criarParcelas(FaturaDTO faturaDTO, LancamentoDTO lancamentoDTO, OAuthUser user);

    Mono<LancamentoDTO> buscarLancamento(ObjectId id);

    Flux<LancamentoDTO> buscarLancamentosDaFatura(ObjectId idFatura);

    Flux<LancamentoDTO> buscarLancamentosDaCarteira(ObjectId idCarteira);
}
