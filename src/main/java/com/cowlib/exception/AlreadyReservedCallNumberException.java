package com.cowlib.exception;

public class AlreadyReservedCallNumberException extends RuntimeException {

    public AlreadyReservedCallNumberException(String message) {
        super(message);
    }

    public AlreadyReservedCallNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
