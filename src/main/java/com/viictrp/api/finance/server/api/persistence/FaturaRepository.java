package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FaturaRepository extends ReactiveCrudRepository<Fatura, ObjectId> {

    Mono<Fatura> findByMes(MesType mesType);

    Mono<Fatura> findByMesAndCartaoId(MesType mes, ObjectId cartaoId);

    Flux<Fatura> findByCartaoId(ObjectId cartaoId);
}
