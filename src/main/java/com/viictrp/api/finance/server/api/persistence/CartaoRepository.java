package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Cartao;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CartaoRepository extends ReactiveCrudRepository<Cartao, ObjectId> {

    Mono<Cartao> findByIdAndUsuarioId(ObjectId id, ObjectId usuarioId);

    Flux<Cartao> findByUsuarioId(ObjectId usuarioId);
}
