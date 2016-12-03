package com.cowlib.controller.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class ErrorResponse extends ResponseEntity {
    private HttpServletRequest request;
    private Throwable exception;

    public ErrorResponse(HttpServletRequest request, HttpStatus httpStatus, Exception exception) {
        super(exception.getMessage(), httpStatus);
        this.request = request;
        this.exception = exception;

    }

    public ErrorResponse logError(LoggingProvider provider) {
        provider.getLogger().error(this.getLoggingMessage(), this.exception);
        return this;
    }

    public String getLoggingMessage() {
        return this.exception.getCause() != null ? this.exception.getClass().getSimpleName() + " caused by " + this.exception.getCause().getClass().getSimpleName() + ": " + this.exception.getMessage() : this.exception.getClass().getSimpleName() + ": " + this.exception.getMessage();
    }
}
