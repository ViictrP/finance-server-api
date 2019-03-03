package com.viictrp.api.finance.server.api.oauth.repository;

import com.viictrp.api.finance.server.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
