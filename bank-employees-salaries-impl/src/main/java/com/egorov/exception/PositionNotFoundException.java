package com.egorov.exception;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(String format) {
        super(format);
    }
}
