package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Lancamento;
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
    void salvarOrcamentoNaCarteira(MesType mes, OAuthUser user, Orcamento orcamento);
    void salvarLancamentoNaCarteira(Lancamento lancamento, OAuthUser user);
    List<Carteira> buscarPorUsuario(OAuthUser user);
}
