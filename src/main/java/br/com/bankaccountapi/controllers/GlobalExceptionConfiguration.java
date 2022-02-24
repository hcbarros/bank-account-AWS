package br.com.bankaccountapi.controllers;

import br.com.bankaccountapi.controllers.response.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionConfiguration extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new ResponseError(errors));
    }


    @ExceptionHandler({RuntimeException.class, EntityNotFoundException.class})
    public ResponseEntity<Object> handleCadastroException(
            RuntimeException exRun, EntityNotFoundException exEnt, WebRequest request) {
        List<String> errors = new ArrayList<String>();

        errors.add(exRun.getMessage());
        errors.add(exEnt.getMessage());

        return ResponseEntity.badRequest().body(new ResponseError(errors));
    }

}