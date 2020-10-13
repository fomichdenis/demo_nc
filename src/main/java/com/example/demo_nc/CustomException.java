package com.example.demo_nc;

public class CustomException extends Exception {
    private String errorCode;
    private String errorDescription;

    public CustomException(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDecription() {
        return errorDescription;
    }

    public void setErrorDecription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
