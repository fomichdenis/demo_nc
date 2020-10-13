package com.example.demo_nc;

public enum CustomErrors {
    USER_WITH_SUCH_USERNAME_FOUND (new CustomException("CE-001", "User with such username was found.")),
    PASSWORD_HAS_INVALID_LENGTH (new CustomException("CE-002", "Password has invalid length"));
    private CustomException exception;

    CustomErrors(CustomException exception) {
        this.exception = exception;
    }

    public CustomException getException() {
        return this.exception;
    }
}
