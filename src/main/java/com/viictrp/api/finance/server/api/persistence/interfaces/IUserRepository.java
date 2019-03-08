package com.viictrp.api.finance.server.api.persistence.interfaces;

import com.viictrp.api.finance.server.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
