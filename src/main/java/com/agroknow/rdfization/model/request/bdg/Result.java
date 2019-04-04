package com.agroknow.rdfization.model.request.bdg;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Result {

    private Double value;

    private String unit;

    public Result() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
