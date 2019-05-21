package com.viictrp.api.finance.server.api.persistence;

import com.viictrp.api.finance.server.api.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByIdAndUsuarioId(Long id, Long userId);
}
