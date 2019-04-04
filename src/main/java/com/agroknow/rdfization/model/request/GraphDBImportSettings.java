package com.agroknow.rdfization.model.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class GraphDBImportSettings {

    private Boolean failOnUnknownDataTypes = Boolean.FALSE;

    private Boolean failOnUnknownLanguageTags = Boolean.FALSE;

    private Boolean normalizeDataTypeValues = Boolean.FALSE;

    private Boolean normalizeLanguageTags = Boolean.FALSE;

    private Boolean preserveBNodeIds = Boolean.TRUE;

    private Boolean stopOnError = Boolean.TRUE;

    private Boolean verifyDataTypeValues = Boolean.FALSE;

    private Boolean verifyLanguageTags = Boolean.FALSE;

    private Boolean verifyRelativeURIs = Boolean.FALSE;

    private Boolean verifyURISyntax = Boolean.FALSE;

    public GraphDBImportSettings() {
    }

    public Boolean getFailOnUnknownDataTypes() {
        return failOnUnknownDataTypes;
    }

    public void setFailOnUnknownDataTypes(Boolean failOnUnknownDataTypes) {
        this.failOnUnknownDataTypes = failOnUnknownDataTypes;
    }

    public Boolean getFailOnUnknownLanguageTags() {
        return failOnUnknownLanguageTags;
    }

    public void setFailOnUnknownLanguageTags(Boolean failOnUnknownLanguageTags) {
        this.failOnUnknownLanguageTags = failOnUnknownLanguageTags;
    }

    public Boolean getNormalizeDataTypeValues() {
        return normalizeDataTypeValues;
    }

    public void setNormalizeDataTypeValues(Boolean normalizeDataTypeValues) {
        this.normalizeDataTypeValues = normalizeDataTypeValues;
    }

    public Boolean getNormalizeLanguageTags() {
        return normalizeLanguageTags;
    }

    public void setNormalizeLanguageTags(Boolean normalizeLanguageTags) {
        this.normalizeLanguageTags = normalizeLanguageTags;
    }

    public Boolean getPreserveBNodeIds() {
        return preserveBNodeIds;
    }

    public void setPreserveBNodeIds(Boolean preserveBNodeIds) {
        this.preserveBNodeIds = preserveBNodeIds;
    }

    public Boolean getStopOnError() {
        return stopOnError;
    }

    public void setStopOnError(Boolean stopOnError) {
        this.stopOnError = stopOnError;
    }

    public Boolean getVerifyDataTypeValues() {
        return verifyDataTypeValues;
    }

    public void setVerifyDataTypeValues(Boolean verifyDataTypeValues) {
        this.verifyDataTypeValues = verifyDataTypeValues;
    }

    public Boolean getVerifyLanguageTags() {
        return verifyLanguageTags;
    }

    public void setVerifyLanguageTags(Boolean verifyLanguageTags) {
        this.verifyLanguageTags = verifyLanguageTags;
    }

    public Boolean getVerifyRelativeURIs() {
        return verifyRelativeURIs;
    }

    public void setVerifyRelativeURIs(Boolean verifyRelativeURIs) {
        this.verifyRelativeURIs = verifyRelativeURIs;
    }

    public Boolean getVerifyURISyntax() {
        return verifyURISyntax;
    }

    public void setVerifyURISyntax(Boolean verifyURISyntax) {
        this.verifyURISyntax = verifyURISyntax;
    }
}
