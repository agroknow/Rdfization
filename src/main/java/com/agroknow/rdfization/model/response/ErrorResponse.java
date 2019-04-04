package com.agroknow.rdfization.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ErrorResponse {

    private String exception;

    private String message;

    public ErrorResponse() {
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;

    }
}
