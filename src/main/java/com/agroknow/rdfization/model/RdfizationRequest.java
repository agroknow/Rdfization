package com.agroknow.rdfization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rdfization_request")
public class RdfizationRequest {

    @Id
    @GeneratedValue
    private Long id;

    private Long startingId;

    private Long endingId;

    private Long noRecords = 0L;

    @Lob
    private String request;

    private String rdfFile;

    private LocalDateTime timestamp;

    private Boolean sentToStore = Boolean.FALSE;

    public RdfizationRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartingId() {
        return startingId;
    }

    public void setStartingId(Long startingId) {
        this.startingId = startingId;
    }

    public Long getNoRecords() {
        return noRecords;
    }

    public void setNoRecords(Long noRecords) {
        this.noRecords = noRecords;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getEndingId() {
        return endingId;
    }

    public void setEndingId(Long endingId) {
        this.endingId = endingId;
    }

    public String getRdfFile() {
        return rdfFile;
    }

    public void setRdfFile(String rdfFile) {
        this.rdfFile = rdfFile;
    }

    public Boolean getSentToStore() {
        return sentToStore;
    }

    public void setSentToStore(Boolean sentToStore) {
        this.sentToStore = sentToStore;
    }
}
