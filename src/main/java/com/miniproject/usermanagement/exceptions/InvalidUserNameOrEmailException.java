package com.miniproject.usermanagement.exceptions;

public class InvalidUserNameOrEmailException extends RuntimeException {
    public InvalidUserNameOrEmailException(String message) {
        super(message);
    }
}
