package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Lancamento;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface LancamentoRepository extends ReactiveCrudRepository<Lancamento, ObjectId> {

    Flux<Lancamento> findByFaturaId(ObjectId faturaId);

    Flux<Lancamento> findByCarteiraId(ObjectId carteiraId);
}
