package com.med.voll.api.infra.exception;

import com.med.voll.api.dto.erro.ErrorMessageDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratamentoDeErro {

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity trataErro404(){
        ErrorMessageDTO error = ErrorMessageDTO.builder()
                .code(HttpStatus.NOT_FOUND.toString())
                .error("NOT FOUND")
                .message("Registro não encontrado")
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity trataErro400(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();
        ErrorMessageDTO error = ErrorMessageDTO.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .error("BAD RESQUEST")
                .message("Requisição inválida")
                .errorsRequest(errors.stream().map(ErroBadRequest::new).toList())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private record ErroBadRequest(String campo, String mensagem){
        public ErroBadRequest(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
