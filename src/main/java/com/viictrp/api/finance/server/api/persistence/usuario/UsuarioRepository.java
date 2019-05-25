package com.viictrp.api.finance.server.api.persistence.usuario;

import com.viictrp.api.finance.server.api.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
