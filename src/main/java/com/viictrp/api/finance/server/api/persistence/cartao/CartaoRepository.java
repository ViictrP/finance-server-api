package com.viictrp.api.finance.server.api.persistence.cartao;

import com.viictrp.api.finance.server.api.domain.Cartao;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartaoRepository extends MongoRepository<Cartao, ObjectId> {

    Optional<Cartao> findByIdAndUsuarioId(ObjectId id, ObjectId usuarioId);

    List<Cartao> findByUsuarioId(ObjectId usuarioId);
}
