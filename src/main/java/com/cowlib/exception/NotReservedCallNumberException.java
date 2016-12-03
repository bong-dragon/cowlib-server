package com.cowlib.exception;


public class NotReservedCallNumberException extends RuntimeException {
    public NotReservedCallNumberException(String message) {
        super(message);
    }

    public NotReservedCallNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
