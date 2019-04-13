package com.viictrp.api.finance.server.api.common;

import com.viictrp.api.finance.server.api.domain.Model;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;

public final class Audity {

    private Audity() {

    }

    public static void audityEntity(Model model, OAuthUser user) {
        model.setCreatedBy(user.getFullname());
        model.setLastModifiedBy(user.getFullname());
    }
}
