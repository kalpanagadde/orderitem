package com.orderitem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class OrderItemExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage) {
        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }

    @ExceptionHandler(OrderItemNotFoundException.class)
    protected ResponseEntity<Object> handleProductNotFoundException(
            OrderItemNotFoundException ex) {
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
        return buildResponseEntity(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach((fieldError) -> errorList.add(fieldError.getDefaultMessage()));
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.BAD_REQUEST)
                .message(errorList.toString()).build();
        return buildResponseEntity(message);
    }
}
