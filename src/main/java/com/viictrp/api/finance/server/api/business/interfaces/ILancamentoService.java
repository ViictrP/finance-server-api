package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.domain.Model;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;

import java.util.List;

public interface ILancamentoService {

    LancamentoDTO salvar(ObjectId id, LancamentoDTO lancamento, OAuthUser user, Class<? extends Model> clazz);

    LancamentoDTO buscarLancamento(ObjectId id);

    List<LancamentoDTO> buscarLancamentosByFatura(ObjectId idFatura);

    List<LancamentoDTO> buscarLancamentosByCarteira(ObjectId idCarteira, OAuthUser user);
}
