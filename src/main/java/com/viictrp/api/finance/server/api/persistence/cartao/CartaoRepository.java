package com.viictrp.api.finance.server.api.persistence.cartao;

import com.viictrp.api.finance.server.api.domain.Cartao;
import com.viictrp.api.finance.server.api.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Optional<Cartao> findByIdAndUsuario(Long id, Usuario usuario);
    List<Cartao> findByUsuario(Usuario usuario);
}
