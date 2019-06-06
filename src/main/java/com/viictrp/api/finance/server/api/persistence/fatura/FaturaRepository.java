package com.viictrp.api.finance.server.api.persistence.fatura;

import com.viictrp.api.finance.server.api.domain.Cartao;
import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

    Optional<Fatura> findByMes(MesType mesType);
    Optional<Fatura> findByMesAndCartao(MesType mes, Cartao cartao);
    List<Fatura> findByCartao(Cartao cartao);
}
