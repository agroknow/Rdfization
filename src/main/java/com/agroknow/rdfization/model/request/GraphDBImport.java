package com.agroknow.rdfization.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize
public class GraphDBImport {

    private String baseURI = null;

    private String chunkSize = null;

    private String context = null;

    private List<String> fileNames = new ArrayList<>();

    private String message = "";

    private String name;

    @JsonProperty(value = "parserSettings")
    private GraphDBImportSettings settings;

    private Long retryTimes = 0L;

    private String status = "PENDING";

    public GraphDBImport() {
    }

    public String getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public String getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(String chunkSize) {
        this.chunkSize = chunkSize;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GraphDBImportSettings getSettings() {
        return settings;
    }

    public void setSettings(GraphDBImportSettings settings) {
        this.settings = settings;
    }

    public Long getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Long retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
