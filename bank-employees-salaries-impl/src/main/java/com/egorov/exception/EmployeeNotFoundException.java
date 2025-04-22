package com.egorov.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String format) {
        super(format);
    }
}
