package com.cowlib.exception;

import java.io.UnsupportedEncodingException;


public class CowlibRuntimeException extends RuntimeException {

    public CowlibRuntimeException(String message) {
        super(message);
    }

    public CowlibRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
