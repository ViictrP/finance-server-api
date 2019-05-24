package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

import java.util.List;

public interface ICarteiraService {

    CarteiraDTO salvar(CarteiraDTO carteiraDTO);
    CarteiraDTO buscarCarteira(Long id, OAuthUser user);
    List<Carteira> buscarPorUsuario(OAuthUser user);
}
