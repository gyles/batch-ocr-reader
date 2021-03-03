/*
 * Copyright Stuff Limited
 */

package com.think.ocr.rest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleIllegalArgumentException(IllegalArgumentException exception,
                                             HttpServletRequest request) {
        log.error("Illegal argument exception occurred.", exception);

        ErrorDto errorDto = new ErrorDto();
        errorDto.setTimestamp(Instant.now());
        errorDto.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDto.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorDto.setPath(request.getRequestURI());
        errorDto.setMessage(exception.getMessage());

        return errorDto;
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorDto handleUncheckedException(RuntimeException exception,
                                             HttpServletRequest request) {
        log.error("Unchecked exception occurred.", exception);

        ErrorDto errorDto = new ErrorDto();
        errorDto.setTimestamp(Instant.now());
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorDto.setPath(request.getRequestURI());
        errorDto.setMessage(exception.getMessage());

        return errorDto;
    }

}
