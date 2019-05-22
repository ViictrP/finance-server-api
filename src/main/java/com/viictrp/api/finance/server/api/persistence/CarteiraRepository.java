package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
