package br.com.fiap.paymentservice.exceptions;

import br.com.fiap.paymentservice.model.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class BadRequestException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseException.class)
    public final ResponseEntity<Object> handleAllExceptions(ResponseException e) {

        ResponseException responseException = new ResponseException(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(responseException, HttpStatus.BAD_REQUEST);
    }
}
