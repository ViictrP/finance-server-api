package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;

import java.util.List;

public interface ICarteiraService {

    CarteiraDTO salvar(CarteiraDTO carteiraDTO, OAuthUser user);
    CarteiraDTO buscarCarteira(ObjectId id, OAuthUser user);
    Carteira buscarCarteira(ObjectId id, Usuario usuario);
    List<Carteira> buscarPorUsuario(OAuthUser user);
}
