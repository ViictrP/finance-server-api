package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Fatura, Long> {

    Optional<Fatura> findByIdAndUsuarioIdAndExcluidoIsFalse(Long id, Long userId);
    List<Fatura> findByUsuarioIdAndExcluidoIsFalse(Long userId);
}
