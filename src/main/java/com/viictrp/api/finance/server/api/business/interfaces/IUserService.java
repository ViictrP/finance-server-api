package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Usuario;
import org.bson.types.ObjectId;

import java.util.List;

public interface IUserService {

    Usuario buscarUsuarioPorId(ObjectId id);

    List<Usuario> buscarUsuarios();
}
