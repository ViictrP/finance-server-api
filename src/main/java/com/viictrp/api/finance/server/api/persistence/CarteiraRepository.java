package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Carteira;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CarteiraRepository extends ReactiveCrudRepository<Carteira, ObjectId> {

    Mono<Carteira> findByIdAndUsuarioId(ObjectId id, ObjectId usuarioId);

    Flux<Carteira> findByUsuarioId(ObjectId usuarioId);
}
