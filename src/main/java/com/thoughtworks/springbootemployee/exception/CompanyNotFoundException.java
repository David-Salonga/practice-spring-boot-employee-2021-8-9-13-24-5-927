package com.thoughtworks.springbootemployee.exception;

public class CompanyNotFoundException extends RuntimeException {
    private String message;

    public CompanyNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
