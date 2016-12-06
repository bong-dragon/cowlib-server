package com.cowlib.controller.helper;


import com.cowlib.exception.AlreadyBorrowedCallNumberException;
import com.cowlib.exception.AlreadyReservedCallNumberException;
import com.cowlib.exception.NotBorrowedCallNumberException;
import com.cowlib.exception.NotReservedCallNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String getLoggingMessage(Exception exception) {
        return exception.getCause() != null ? exception.getClass().getSimpleName() + " caused by " + exception.getCause().getClass().getSimpleName() + ": " + exception.getMessage() : exception.getClass().getSimpleName() + ": " + exception.getMessage();
    }

    private ResponseEntity<ErrorResponse> getResponseEntity(HttpStatus httpStatus, Exception e) {
        logger.warn(getLoggingMessage(e));
        return new ResponseEntity<>(new ErrorResponse(httpStatus.value(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(AlreadyBorrowedCallNumberException.class)
    public ResponseEntity<ErrorResponse> alreadyBorrowedCallNumberException(Exception e) {
        return getResponseEntity(HttpStatus.FORBIDDEN, e);
    }

    @ExceptionHandler(AlreadyReservedCallNumberException.class)
    public ResponseEntity<ErrorResponse> alreadyReservedCallNumberException(Exception e) {
        return getResponseEntity(HttpStatus.FORBIDDEN, e);
    }

    @ExceptionHandler(NotBorrowedCallNumberException.class)
    public ResponseEntity<ErrorResponse> notBorrowedCallNumberException(Exception e) {
        return getResponseEntity(HttpStatus.FORBIDDEN, e);
    }

    @ExceptionHandler(NotReservedCallNumberException.class)
    public ResponseEntity<ErrorResponse> notReservedCallNumberException(Exception e) {
        return getResponseEntity(HttpStatus.FORBIDDEN, e);
    }

}
