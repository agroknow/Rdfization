package com.agroknow.rdfization.gardian.semantics.json;

import com.google.gson.JsonArray;

public class Header {
    private String headerName;
    private String field;
    private boolean editable;
    private String currentType;
    private boolean isNameValid;
    private boolean checked;
    private String type;
    private boolean validType;
    private boolean validFormat;
    private boolean validMeasure;
    private boolean validVoc;
    private String format;
    private String vocabulary;
    private ClassOf classOf;
    private  Measure measure;
    private JsonArray data;

    public Header(String headerName, String field, boolean editable, String currentType, boolean isNameValid, boolean checked, String type, boolean validType, boolean validFormat, boolean validMeasure, boolean validVoc, String format, String vocabulary, ClassOf classOf, Measure measure, JsonArray data) {
        this.headerName = headerName;
        this.field = field;
        this.editable = editable;
        this.currentType = currentType;
        this.isNameValid = isNameValid;
        this.checked = checked;
        this.type = type;
        this.validType = validType;
        this.validFormat = validFormat;
        this.validMeasure = validMeasure;
        this.validVoc = validVoc;
        this.format = format;
        this.vocabulary = vocabulary;
        this.classOf = classOf;
        this.measure = measure;
        this.data = data;
    }

    public JsonArray getData() {
        return data;
    }

    public void setData(JsonArray data) {
        this.data = data;
    }


    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    public boolean isNameValid() {
        return isNameValid;
    }

    public void setNameValid(boolean nameValid) {
        isNameValid = nameValid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isValidType() {
        return validType;
    }

    public void setValidType(boolean validType) {
        this.validType = validType;
    }

    public boolean isValidFormat() {
        return validFormat;
    }

    public void setValidFormat(boolean validFormat) {
        this.validFormat = validFormat;
    }

    public boolean isValidMeasure() {
        return validMeasure;
    }

    public void setValidMeasure(boolean validMeasure) {
        this.validMeasure = validMeasure;
    }

    public boolean isValidVoc() {
        return validVoc;
    }

    public void setValidVoc(boolean validVoc) {
        this.validVoc = validVoc;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public ClassOf getClassOf() {
        return classOf;
    }

    public void setClassOf(ClassOf classOf) {
        this.classOf = classOf;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }



}
