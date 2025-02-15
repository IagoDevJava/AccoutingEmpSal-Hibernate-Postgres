package com.anybank.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String format) {
        super(format);
    }
}
