package com.viictrp.api.finance.server.api.common;

import com.viictrp.api.finance.server.api.exception.ResourceBusinessException;

public final class PreconditionsRest {

    private PreconditionsRest() {

    }

    public static void checkCondition(boolean condition, String message) {
        if (!condition) {
            throw new ResourceBusinessException(message);
        }
    }
}
