package com.agroknow.rdfization.controller;


import com.agroknow.rdfization.gardian.semantics.importer.JSONImporter;
import com.agroknow.rdfization.model.RdfizationRequest;
import com.agroknow.rdfization.model.apigea.ApigeaData;
import com.agroknow.rdfization.model.request.bdg.BDGConversionRequest;
import com.agroknow.rdfization.repository.RdfizationRequestRepository;
import com.agroknow.rdfization.service.BDGRdfFileGenerationService;
import com.agroknow.rdfization.service.DatasetUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

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

    @RequestMapping(method = RequestMethod.POST, path = "/symbeeosis/model2rdf", produces = {"application/rdf+xml"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    byte[] symbeeosis2Rdf(@RequestBody List<ApigeaData> data) throws Exception {

        String jsonString = new ObjectMapper().writeValueAsString(data);

        data.forEach(ApigeaData::generate);

        RdfizationRequest request = repository.findByRequest(jsonString).orElse(new RdfizationRequest());

        if (request.getId() != null) {
            InputStream in = new FileInputStream(request.getRdfFile());
            return IOUtils.toByteArray(in);
        }

        request.setTimestamp(LocalDateTime.now());
        request.setRequest(jsonString);
        request.setStartingId(null);
        request.setEndingId(null);
        request.setRdfFile(fileGenerationService.generateApigeaRdf(data));

        try {
            service.upload(request.getRdfFile());
            request.setSentToStore(Boolean.TRUE);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        repository.save(request);

        InputStream in = new FileInputStream(request.getRdfFile());
        return IOUtils.toByteArray(in);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/model2rdf", produces = {"application/rdf+xml"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    byte[] model2Rdf(@RequestBody List<ApigeaData> data, @RequestParam("model") String model) throws Exception {

        String jsonString = new ObjectMapper().writeValueAsString(data);

        data.forEach(ApigeaData::generate);

        RdfizationRequest request = repository.findByRequest(jsonString).orElse(new RdfizationRequest());

        if (request.getId() != null) {
            InputStream in = new FileInputStream(request.getRdfFile());
            return IOUtils.toByteArray(in);
        }

        request.setTimestamp(LocalDateTime.now());
        request.setRequest(jsonString);
        request.setStartingId(null);
        request.setEndingId(null);
        request.setRdfFile(fileGenerationService.generateApigeaRdf(data));

        try {
            service.upload(request.getRdfFile());
            request.setSentToStore(Boolean.TRUE);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        repository.save(request);

        InputStream in = new FileInputStream(request.getRdfFile());
        return IOUtils.toByteArray(in);

    }

}
