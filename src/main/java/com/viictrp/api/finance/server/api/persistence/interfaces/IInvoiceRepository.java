package com.viictrp.api.finance.server.api.persistence.interfaces;

import com.viictrp.api.finance.server.api.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findByIdAndUserId(Long id, Long userId);
    List<Invoice> findByUserId(Long userId);
}
