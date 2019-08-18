package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Usuario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {

}
