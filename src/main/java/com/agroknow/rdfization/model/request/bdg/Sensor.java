package com.agroknow.rdfization.model.request.bdg;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Sensor {

    private String name;

    private Double latitude;

    private Double longitude;

    public Sensor() {
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
