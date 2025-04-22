package com.egorov.exception;

public class AttendanceDataNotFoundException extends RuntimeException {
    public AttendanceDataNotFoundException(String message) {
        super(message);
    }
}
