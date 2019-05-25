package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Orcamento;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

import java.util.List;

public interface ICarteiraService {

    CarteiraDTO salvar(CarteiraDTO carteiraDTO);
    CarteiraDTO buscarCarteira(Long id, OAuthUser user);
    Carteira buscarCarteira(Long id, Usuario usuario);
    List<Carteira> buscarPorUsuario(OAuthUser user);
    void salvarOrcamentoNaCarteira(MesType mes, OAuthUser user, Orcamento orcamento);
}
