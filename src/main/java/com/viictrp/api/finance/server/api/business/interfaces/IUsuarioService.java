package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Usuario;
import org.bson.types.ObjectId;

public interface IUsuarioService {

    Usuario buscarUsuario(ObjectId id);
}
