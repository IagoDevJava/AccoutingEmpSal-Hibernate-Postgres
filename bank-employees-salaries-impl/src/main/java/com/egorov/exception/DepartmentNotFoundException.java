package com.egorov.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String format) {
        super(format);
    }
}
