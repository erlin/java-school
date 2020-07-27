package org.nilre.urlshortener.controllers;

import org.nilre.urlshortener.shorteners.utils.ApplicationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class LocalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ApplicationException.class})
    public ResponseEntity handleApplicationException(ApplicationException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Object obj = ex.getMessage();
        HashMap<String, Object> body = new HashMap() {{
            put("error", obj);
        }};
        return handleExceptionInternal(ex, body, headers, HttpStatus.NOT_ACCEPTABLE, request);
    }
}
