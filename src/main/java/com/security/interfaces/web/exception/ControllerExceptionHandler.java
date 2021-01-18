package com.security.interfaces.web.exception;

import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.security.infrastructure.common.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    public ControllerExceptionHandler() { super(); }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).errorMessage("Unknown error occurred").build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorResponse> handleNotAuthorizedException(NotAuthorizedException e) {
        ErrorResponse errorResponse = ErrorResponse.builder().httpStatus(HttpStatus.UNAUTHORIZED).errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<ErrorResponse> handleUsernameExistsException(UsernameExistsException e) {
        ErrorResponse errorResponse = ErrorResponse.builder().httpStatus(HttpStatus.CONFLICT).errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
