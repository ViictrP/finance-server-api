package com.viictrp.api.finance.server.api.persistence.lancamento;

import com.viictrp.api.finance.server.api.domain.Lancamento;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LancamentoRepository extends MongoRepository<Lancamento, ObjectId> {

    List<Lancamento> findByFaturaId(ObjectId faturaId);
    List<Lancamento> findByCarteiraId(ObjectId carteiraId);
}
