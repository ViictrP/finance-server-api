package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findByIdAndUserIdAndExcluidoIsFalse(Long id, Long userId);
    List<Invoice> findByUserIdAndExcluidoIsFalse(Long userId);
}
