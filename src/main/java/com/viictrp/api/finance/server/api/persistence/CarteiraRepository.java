package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Optional<Carteira> findByMesAndUsuario(MesType mes, Usuario usuario);
    Optional<Carteira> findByIdAndUsuario(Long id, Usuario usuario);
    List<Carteira> findByUsuario(Usuario usuario);
}
