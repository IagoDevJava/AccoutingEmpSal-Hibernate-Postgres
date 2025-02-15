package com.anybank.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String format) {
        super(format);
    }
}
