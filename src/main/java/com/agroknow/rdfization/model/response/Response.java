package com.agroknow.rdfization.model.response;

import org.springframework.http.HttpStatus;

public class Response {

    private HttpStatus status;

    private String body;

    public Response() {
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

