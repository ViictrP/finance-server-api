package com.viictrp.api.finance.server.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerRequest extends ResponseEntityExceptionHandler {

    /** Método para lançar Exception.
     * @param e Exception
     * @return ResponseEntity
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(e.getMessage());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro", e.getClass().getName());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatusCode()));
    }

    /** Método para lançar CustomException.
     * @param e CustomGenericException
     * @return ResponseEntity
     *
     */
    @ExceptionHandler({ HttpClientErrorException.class })
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ApiError(e.getStatusCode(), e.getStatusText(), e.getClass().getName()),
                new HttpHeaders(),
                e.getStatusCode());
    }
}
