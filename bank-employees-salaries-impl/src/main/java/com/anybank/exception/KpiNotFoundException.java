package com.anybank.exception;

public class KpiNotFoundException extends RuntimeException {
    public KpiNotFoundException(String message) {
        super(message);
    }
}
