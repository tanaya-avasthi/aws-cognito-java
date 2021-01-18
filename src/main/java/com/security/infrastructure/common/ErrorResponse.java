package com.security.infrastructure.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String errorMessage;

    public ErrorResponse(HttpStatus httpStatus, Throwable t) {
        this.httpStatus = httpStatus;
        this.errorMessage = t.getMessage();
    }

    public ErrorResponse(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
