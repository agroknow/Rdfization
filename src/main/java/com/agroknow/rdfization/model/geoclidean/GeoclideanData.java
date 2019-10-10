package com.agroknow.rdfization.model.geoclidean;

import com.agroknow.rdfization.model.base.Dataset;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.cyberborean.rdfbeans.annotations.RDF;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.cyberborean.rdfbeans.annotations.RDFSubject;

import java.util.Date;

@RDFBean("qb:Obesrvation")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/",
        "geo = http://www.w3.org/2003/01/geo/wgs84_pos#",
        "qb = http://purl.org/linked-data/cube#",
        "rdfs = http://www.w3.org/2000/01/rdf-schema#"
})
@JsonSerialize
public class GeoclideanData {

    private String id;

    private Dataset dataset;

    private String date;

    private Double min;

    private Double max;

    private Double mean;

    private Double sum;

    private Double std;

    private BDGSatellite satellite;

    public GeoclideanData() {
    }

    public GeoclideanData(Dataset dataset, String date, Double min, Double max, Double mean, Double sum, Double std, BDGSatellite satellite, String parcelId, String indicator) {
        this.dataset = dataset;
        this.date = date;
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.sum = sum;
        this.std = std;
        this.satellite = satellite;

        this.id = indicator + "/" + parcelId + "/" + date;
    }

    @RDFSubject(prefix = "http://dev.bigdatagrapes.eu/data/geoclidean/")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @RDF("qb:dataSet")
    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    @RDF("bdg:date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @RDF("bdg:NDRE_MIN")
    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    @RDF("bdg:NDRE_MAX")
    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @RDF("bdg:NDRE_MEAN")
    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    @RDF("bdg:NDRE_SUM")
    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @RDF("bdg:NDRE_STD")
    public Double getStd() {
        return std;
    }

    public void setStd(Double std) {
        this.std = std;
    }

    @RDF("bdg:satellite")
    public BDGSatellite getSatellite() {
        return satellite;
    }

    public void setSatellite(BDGSatellite satellite) {
        this.satellite = satellite;
    }
}
