package com.agroknow.rdfization.model.geoclidean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@JsonSerialize
public class FieldIndicator implements Serializable {

    @Id
    private String id;

    private String pilot;

    private String date;

    private String name;

    private Double value;

    private Field belongsIn;

    private Double maxValue;

    private Double minValue;

    private Double meanValue;

    private Double stdValue;

    private Integer countValue;

    private Double sumValue;

    private String source;

    public FieldIndicator() {
    }

    public FieldIndicator(String date, String name, Double value, Field belongsIn, Double maxValue, Double minValue, Double meanValue, Double stdValue, Integer countValue, Double sumValue, String source) {
        this.date = date;
        this.name = name;
        this.value = value;
        this.belongsIn = belongsIn;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.meanValue = meanValue;
        this.stdValue = stdValue;
        this.countValue = countValue;
        this.sumValue = sumValue;
        this.source = source;

        this.id = belongsIn.getId() + "_" + date + "_" + name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPilot() {
        return pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Field getBelongsIn() {
        return belongsIn;
    }

    public void setBelongsIn(Field belongsIn) {
        this.belongsIn = belongsIn;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMeanValue() {
        return meanValue;
    }

    public void setMeanValue(Double meanValue) {
        this.meanValue = meanValue;
    }

    public Double getStdValue() {
        return stdValue;
    }

    public void setStdValue(Double stdValue) {
        this.stdValue = stdValue;
    }

    public Integer getCountValue() {
        return countValue;
    }

    public void setCountValue(Integer countValue) {
        this.countValue = countValue;
    }

    public Double getSumValue() {
        return sumValue;
    }

    public void setSumValue(Double sumValue) {
        this.sumValue = sumValue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
