package com.cowlib.exception;

public class NotBorrowedCallNumberException extends RuntimeException {

    public NotBorrowedCallNumberException(String message) {
        super(message);
    }

    public NotBorrowedCallNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
