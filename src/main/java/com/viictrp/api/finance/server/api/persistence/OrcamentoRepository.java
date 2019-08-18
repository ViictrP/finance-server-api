package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Orcamento;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OrcamentoRepository extends ReactiveCrudRepository<Orcamento, ObjectId> {

    Mono<Orcamento> findByMes(MesType mes);

    Mono<Orcamento> findByCarteiraIdAndId(ObjectId carteiraId, ObjectId id);
}
