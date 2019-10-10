package com.agroknow.rdfization.service;

import com.agroknow.rdfization.model.request.GraphDBImport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class DatasetImportService {

    @Value("${gdb.import.endpoint}")
    private String importEndpoint;

    @Value("${gdb.import.baseDir}")
    private String baseDir;

    @Value("${gdb.import-test.endpoint}")
    private String testImportEndpoint;

    public void importDataset(String remotePath, boolean test) throws Exception{
        GraphDBImport dbImport = new GraphDBImport();
        dbImport.setName(baseDir + remotePath);
        dbImport.getFileNames().add(baseDir + remotePath);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity request = new HttpEntity(objectMapper.writeValueAsString(dbImport), headers);

        String endpoint = test ? testImportEndpoint : importEndpoint;

        restTemplate.exchange(endpoint, HttpMethod.POST, request, Object.class);
    }

}
