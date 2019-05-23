package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Optional<Carteira> findByMesAndUsuarioId(String mes, Long usuarioID);
}
