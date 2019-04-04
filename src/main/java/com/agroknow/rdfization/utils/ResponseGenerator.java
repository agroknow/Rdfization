package com.agroknow.rdfization.utils;

import com.agroknow.rdfization.model.response.ErrorResponse;
import com.agroknow.rdfization.model.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

public class ResponseGenerator {

    public static void generateErrorMessage(Response response, HttpStatus status, String exception, String message) {

        ErrorResponse error = new ErrorResponse();
        error.setException(exception);
        error.setMessage(message);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = "";

        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(error);
        } catch(JsonProcessingException ignored) {
        }

        response.setStatus(status);
        response.setBody(jsonResult);
    }

    public static void generateValidResponse(Response response, Object object) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);

        response.setStatus(HttpStatus.OK);
        response.setBody(jsonResult);
    }
}
