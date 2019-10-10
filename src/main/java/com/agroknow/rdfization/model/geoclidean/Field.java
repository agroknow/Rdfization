package com.agroknow.rdfization.model.geoclidean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@JsonSerialize
public class Field implements Serializable {

    private String id;

    private String name;

    private String crop;

    private Integer parcelId;

    public Field() {
    }

    public Field(String name, String crop, Integer parcelId) {
        this.name = name;
        this.crop = crop;
        this.parcelId = parcelId;

        this.id = "FLD_" + parcelId;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public Integer getParcelId() {
        return parcelId;
    }

    public void setParcelId(Integer parcelId) {
        this.parcelId = parcelId;
    }
}
