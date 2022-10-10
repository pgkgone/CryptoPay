package com.cryptopay.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.NoSuchFileException;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> noSuchElementResponse(
            Exception ex,
            WebRequest request)
    {
        return logAndReturn(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> errorResponse(
            Exception ex,
            WebRequest request)
    {
        return logAndReturn(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private ResponseEntity<ErrorResponse> logAndReturn(HttpStatus httpStatus, Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse(
                        httpStatus.value(),
                        ex.getMessage()
                ),
                httpStatus
        );
    }


}
