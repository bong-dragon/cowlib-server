package com.cowlib.exception;

public class AlreadyBorrowedCallNumberException extends RuntimeException {
    public AlreadyBorrowedCallNumberException(String message) {
        super(message);
    }

    public AlreadyBorrowedCallNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
