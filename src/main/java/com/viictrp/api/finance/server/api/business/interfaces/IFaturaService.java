package com.viictrp.api.finance.server.api.business.interfaces;

import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.bson.types.ObjectId;

import java.util.List;

public interface IFaturaService {

    FaturaDTO salvar(ObjectId cartaoId, FaturaDTO faturaDTO, OAuthUser user);
    FaturaDTO buscarFatura(ObjectId id);
    List<FaturaDTO> buscarFaturas(ObjectId idCartao, OAuthUser user);
}
