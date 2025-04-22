package com.egorov.exception;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(String format) {
        super(format);
    }
}
