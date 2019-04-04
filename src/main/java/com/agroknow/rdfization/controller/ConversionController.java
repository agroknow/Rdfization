package com.agroknow.rdfization.controller;


import com.agroknow.rdfization.gardian.semantics.importer.JSONImporter;
import com.agroknow.rdfization.model.RdfizationRequest;
import com.agroknow.rdfization.model.request.bdg.BDGConversionRequest;
import com.agroknow.rdfization.model.response.Response;
import com.agroknow.rdfization.repository.RdfizationRequestRepository;
import com.agroknow.rdfization.service.BDGRdfFileGenerationService;
import com.agroknow.rdfization.service.DatasetUploadService;
import com.agroknow.rdfization.utils.ResponseGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/convert")
@CrossOrigin(origins = "*")
public class ConversionController {

    @Autowired
    private RdfizationRequestRepository repository;

    @Autowired
    private DatasetUploadService service;

    @Autowired
    private BDGRdfFileGenerationService fileGenerationService;

    @RequestMapping(method = RequestMethod.POST, path = "/jsontordf", produces = {"application/rdf+xml"},
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    byte[] jsonToRdf(@RequestBody MultiValueMap<String, String> data) throws Exception {

        String body = data.get("json").get(0);

        RdfizationRequest request = repository.findByRequest(body).orElse(new RdfizationRequest());

        if (request.getId() != null) {
            InputStream in = new FileInputStream(request.getRdfFile());
            return IOUtils.toByteArray(in);
        }

        request.setTimestamp(LocalDateTime.now());
        request.setRequest(body);
        Long startingId = repository.findMaxGivenId();
        if (startingId == null) {
            startingId = 0L;
        }
        request.setStartingId(startingId);

        JSONImporter importer = new JSONImporter(body, false, request, startingId);

        request.setEndingId(startingId + request.getNoRecords());

        String rdfFile = importer.getOutputFilename();
        String fullPath = importer.getFullPath();
        request.setRdfFile(fullPath);

        try {
            service.upload(fullPath);
            request.setSentToStore(Boolean.TRUE);
        } catch (Exception ignored) {

        }

        repository.save(request);

        InputStream in = new FileInputStream(fullPath);
        return IOUtils.toByteArray(in);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/sensor2bdg", produces = {"application/rdf+xml"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    byte[] sensor2Bdg(@RequestBody BDGConversionRequest data) throws Exception {

        String jsonString = new ObjectMapper().writeValueAsString(data);

        RdfizationRequest request = repository.findByRequest(jsonString).orElse(new RdfizationRequest());

        if (request.getId() != null) {
            InputStream in = new FileInputStream(request.getRdfFile());
            return IOUtils.toByteArray(in);
        }

        request.setTimestamp(LocalDateTime.now());
        request.setRequest(jsonString);
        request.setStartingId(null);
        request.setEndingId(null);
        request.setRdfFile(fileGenerationService.generateRdf(data));

        try {
            service.upload(request.getRdfFile());
            request.setSentToStore(Boolean.TRUE);
        } catch (Exception ignored) {

        }

        repository.save(request);

        InputStream in = new FileInputStream(request.getRdfFile());
        return IOUtils.toByteArray(in);

    }

}
