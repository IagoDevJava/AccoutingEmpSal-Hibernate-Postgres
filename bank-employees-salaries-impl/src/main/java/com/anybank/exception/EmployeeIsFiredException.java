package com.anybank.exception;

public class EmployeeIsFiredException extends RuntimeException {
    public EmployeeIsFiredException(String message) {
        super(message);
    }
}
