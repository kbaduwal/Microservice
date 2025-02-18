package com.usermanagementservice.exception;

import com.usermanagementservice.playload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException exception, WebRequest webRequest){

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                "Validation Failed",
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );

        exception.getBindingResult().getFieldErrors()
                .forEach(
                        error ->{
                            errorResponse.addValidationError(
                                    error.getField() + ": " + error.getDefaultMessage()
                            );
                        }
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    //Handle generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception exception, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
