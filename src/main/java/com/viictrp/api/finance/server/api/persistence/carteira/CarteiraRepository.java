package com.viictrp.api.finance.server.api.persistence.carteira;

import com.viictrp.api.finance.server.api.domain.Carteira;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarteiraRepository extends MongoRepository<Carteira, ObjectId> {

    Optional<Carteira> findByIdAndUsuarioId(ObjectId id, ObjectId usuarioId);
    List<Carteira> findByUsuarioId(ObjectId usuarioId);
}
