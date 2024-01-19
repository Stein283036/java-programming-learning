package com.stein.exception;

/**
 * @author njl
 * @date 2023/2/19
 */
public class MyException extends RuntimeException {
    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
