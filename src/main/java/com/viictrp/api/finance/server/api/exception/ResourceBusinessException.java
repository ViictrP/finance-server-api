package com.viictrp.api.finance.server.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ResourceBusinessException extends HttpClientErrorException {

    public ResourceBusinessException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}