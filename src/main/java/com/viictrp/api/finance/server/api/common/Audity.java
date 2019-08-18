package com.viictrp.api.finance.server.api.common;

import com.viictrp.api.finance.server.api.domain.Model;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import org.joda.time.DateTime;

public final class Audity {

    private Audity() {

    }

    public static void audityEntity(OAuthUser user, Model... models) {
        for (Model model : models) {
            model.setCreatedBy(user.getFullname());
            model.setLastModifiedBy(user.getFullname());
            model.setCreateDate(DateTime.now());
            model.setLastModifiedDate(DateTime.now());
        }
    }
}
