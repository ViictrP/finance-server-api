package com.viictrp.api.finance.server.api.persistence.lancamento;

import com.viictrp.api.finance.server.api.domain.Cartao;
import com.viictrp.api.finance.server.api.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    Optional<Lancamento> findByIdAndCartao(Long id, Cartao cartao);
    List<Lancamento> findByCartao(Cartao cartao);
}
