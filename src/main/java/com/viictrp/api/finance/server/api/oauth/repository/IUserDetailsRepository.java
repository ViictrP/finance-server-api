package com.viictrp.api.finance.server.api.oauth.repository;

import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDetailsRepository extends MongoRepository<OAuthUser, ObjectId> {

    Optional<OAuthUser> findByUsername(String username);
    Optional<OAuthUser> findByUserId(Long id);
}
