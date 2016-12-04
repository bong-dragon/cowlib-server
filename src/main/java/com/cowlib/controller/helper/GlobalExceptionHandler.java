package com.cowlib.controller.helper;


import com.cowlib.exception.AlreadyBorrowedCallNumberException;
import com.cowlib.exception.AlreadyReservedCallNumberException;
import com.cowlib.exception.NotBorrowedCallNumberException;
import com.cowlib.exception.NotReservedCallNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler implements LoggingProvider {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(AlreadyBorrowedCallNumberException.class)
    public ErrorResponse alreadyBorrowedCallNumberException(HttpServletRequest req, AlreadyBorrowedCallNumberException e) {
        return new ErrorResponse(req, HttpStatus.FORBIDDEN, e).logError(this);
    }

    @ExceptionHandler(AlreadyReservedCallNumberException.class)
    public ErrorResponse alreadyReservedCallNumberException(HttpServletRequest req, AlreadyBorrowedCallNumberException e) {
        return new ErrorResponse(req, HttpStatus.FORBIDDEN, e).logError(this);
    }

    @ExceptionHandler(NotBorrowedCallNumberException.class)
    public ErrorResponse notBorrowedCallNumberException(HttpServletRequest req, AlreadyBorrowedCallNumberException e) {
        return new ErrorResponse(req, HttpStatus.FORBIDDEN, e).logError(this);
    }

    @ExceptionHandler(NotReservedCallNumberException.class)
    public ErrorResponse notReservedCallNumberException(HttpServletRequest req, AlreadyBorrowedCallNumberException e) {
        return new ErrorResponse(req, HttpStatus.FORBIDDEN, e).logError(this);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

}
