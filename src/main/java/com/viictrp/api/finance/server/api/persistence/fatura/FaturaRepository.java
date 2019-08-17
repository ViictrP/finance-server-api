package com.viictrp.api.finance.server.api.persistence.fatura;

import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaturaRepository extends MongoRepository<Fatura, ObjectId> {

    Optional<Fatura> findByMes(MesType mesType);

    Optional<Fatura> findByMesAndCartaoId(MesType mes, ObjectId cartaoId);

    List<Fatura> findByCartaoId(ObjectId cartaoId);
}
