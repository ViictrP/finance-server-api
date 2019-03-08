package com.viictrp.api.finance.server.api.exception;

import org.springframework.http.HttpStatus;

public class ApiError {

    private final String statusMessage;
    private final Integer statusCode;
    private final String message;
    private final String exception;

    /** Construtor ApiError. */
    public ApiError(HttpStatus status, String message, String exception) {
        this.statusMessage = status.getReasonPhrase();
        this.exception = exception;
        this.statusCode = status.value();
        this.message = message;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
