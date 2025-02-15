package com.anybank.exception;

public class SalariesDataNotFoundException extends RuntimeException {
    public SalariesDataNotFoundException(String message) {
        super(message);
    }
}
