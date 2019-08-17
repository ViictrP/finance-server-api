package com.viictrp.api.finance.server.api.persistence.orcamento;

import com.viictrp.api.finance.server.api.domain.Orcamento;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrcamentoRepository extends MongoRepository<Orcamento, ObjectId> {

    Optional<Orcamento> findByMes(MesType mes);
    Optional<Orcamento> findByCarteiraIdAndId(ObjectId carteiraId, ObjectId id);
}
