package com.AcadianaPower.Validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;

@ControllerAdvice
public class ValidationResponse extends ResponseEntityExceptionHandler {

    @Override
    protected @NonNull ResponseEntity<Object>
    handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull      HttpHeaders headers,
            @NonNull       HttpStatus status,
            @NonNull       WebRequest request)

    {
        HashMap<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errorMap.put( ((FieldError) error).getField() ,error.getDefaultMessage());
        });
        return new ResponseEntity<Object>(errorMap,HttpStatus.BAD_REQUEST);

    }
}

