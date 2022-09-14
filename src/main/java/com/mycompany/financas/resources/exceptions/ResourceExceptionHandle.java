package com.mycompany.financas.resources.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import com.mycompany.financas.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class ResourceExceptionHandle {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,
                                                        HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler(ConstraintViolationException.class)
 public ResponseEntity<List<String>> handleConstraint(ConstraintViolationException e){
        Stream<String> listaDeErros = e.getConstraintViolations().stream().map(violation -> violation.getPropertyPath() + ":" + violation.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaDeErros.collect(Collectors.toList()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handlerMethodArgument(MethodArgumentNotValidException method  ){
        Stream<String> listaDeErros = method.getBindingResult().getAllErrors().stream().map(error -> ((FieldError) error).getField() + ":" + error.getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaDeErros.collect(Collectors.toList()));
    }


}