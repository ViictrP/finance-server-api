package com.viictrp.api.finance.server.api.persistence.categoria;

import com.viictrp.api.finance.server.api.domain.Categoria;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CategoriaRepository extends ReactiveCrudRepository<Categoria, ObjectId> {

    Mono<Categoria> findByIdAndUsuarioId(ObjectId id, ObjectId userId);

    Flux<Categoria> findByUsuarioId(ObjectId userId);
}
