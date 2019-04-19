package com.agroknow.rdfization.model.apigea;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.cyberborean.rdfbeans.annotations.RDF;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.cyberborean.rdfbeans.annotations.RDFSubject;

import java.util.Date;
import java.util.UUID;

@RDFBean("qb:Obesrvation")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/",
        "geo = http://www.w3.org/2003/01/geo/wgs84_pos#",
        "qb = http://purl.org/linked-data/cube#",
        "rdfs = http://www.w3.org/2000/01/rdf-schema#"
})
@JsonSerialize
public class ApigeaData {

    // Toxicity on skin cells (MTT assay)	Gene expression (SIRT1) on skin cells

    @JsonProperty(value = "Vineyard Id")
    private String vineyardId;

    @JsonProperty(value = "Vineyard")
    private String vineyard;

    @JsonProperty(value = "Region")
    private String region;

    @JsonProperty(value = "City")
    private String city;

    @JsonProperty(value = "Parcel ID")
    private String parcelId;

    @JsonProperty(value = "Sample")
    private String id;

    private String dataset = "cosmetics";

    @JsonProperty(value = "Extraction Method")
    private String extractionMethod;

    @JsonProperty(value = "Variety")
    private String grapeVariety;

    @JsonProperty(value = "Sample collection day")
    private Date date;

    @JsonProperty(value = "pH")
    private Double pH;

    @JsonProperty(value = "Refractive index")
    private Double refractiveIndex;

    @JsonProperty(value = "Total microbial count")
    private String totalMicrobialCount;

    @JsonProperty(value = "Yeasts and moulds")
    private String yeastsAndMoulds;

    @JsonProperty(value = "Antioxidant activity DPPH (μg/mL trolox)")
    private Double antioxidantActivityDPPHTrolox;

    @JsonProperty(value = "Antioxidant activity ABTS (μg/mL trolox)")
    private Double antioxidantActivityABTSTrolox;

    @JsonProperty(value = "Total phenolic content, TPC (μg/mL gallic acid)")
    private Double tpcGallicAcid;

    @JsonProperty(value = "Total flavonoid content, TFC (μg/mL quercetin")
    private Double tfcQuercetin;

    public ApigeaData() {
    }

    public ApigeaData(String dataset, String extractionMethod, String grapeVariety, Date date, Double pH,
                      Double refractiveIndex, String totalMicrobialCount, String yeastsAndMoulds,
                      Double antioxidantActivityDPPHTrolox, Double antioxidantActivityABTSTrolox,
                      Double tpcGallicAcid, Double tfcQuercetin) {
        this.dataset = dataset;
        this.extractionMethod = extractionMethod;
        this.grapeVariety = grapeVariety;
        this.date = date;
        this.pH = pH;
        this.refractiveIndex = refractiveIndex;
        this.totalMicrobialCount = totalMicrobialCount;
        this.yeastsAndMoulds = yeastsAndMoulds;
        this.antioxidantActivityDPPHTrolox = antioxidantActivityDPPHTrolox;
        this.antioxidantActivityABTSTrolox = antioxidantActivityABTSTrolox;
        this.tpcGallicAcid = tpcGallicAcid;
        this.tfcQuercetin = tfcQuercetin;
    }

    @RDFSubject(prefix = "http://dev.bigdatagrapes.eu/data/cosmetics/")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @RDF("qb:dataSet")
    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    @RDF("bdg:extractionMethod")
    public String getExtractionMethod() {
        return extractionMethod;
    }

    public void setExtractionMethod(String extractionMethod) {
        this.extractionMethod = extractionMethod;
    }

    @RDF("bdg:grapeVariety")
    public String getGrapeVariety() {
        return grapeVariety;
    }

    public void setGrapeVariety(String grapeVariety) {
        this.grapeVariety = grapeVariety;
    }

    @RDF("bdg:date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @RDF("bdg:pH")
    public Double getpH() {
        return pH;
    }

    public void setpH(Double pH) {
        this.pH = pH;
    }

    @RDF("bdg:refractiveIndex")
    public Double getRefractiveIndex() {
        return refractiveIndex;
    }

    public void setRefractiveIndex(Double refractiveIndex) {
        this.refractiveIndex = refractiveIndex;
    }

    @RDF("bdg:totalMicrobialCount")
    public String getTotalMicrobialCount() {
        return totalMicrobialCount;
    }

    public void setTotalMicrobialCount(String totalMicrobialCount) {
        this.totalMicrobialCount = totalMicrobialCount;
    }

    @RDF("bdg:yeastsAndMoulds")
    public String getYeastsAndMoulds() {
        return yeastsAndMoulds;
    }

    public void setYeastsAndMoulds(String yeastsAndMoulds) {
        this.yeastsAndMoulds = yeastsAndMoulds;
    }

    @RDF("bdg:antioxidantActivityDPPHTrolox")
    public Double getAntioxidantActivityDPPHTrolox() {
        return antioxidantActivityDPPHTrolox;
    }

    public void setAntioxidantActivityDPPHTrolox(Double antioxidantActivityDPPHTrolox) {
        this.antioxidantActivityDPPHTrolox = antioxidantActivityDPPHTrolox;
    }

    @RDF("bdg:antioxidantActivityABTSTrolox")
    public Double getAntioxidantActivityABTSTrolox() {
        return antioxidantActivityABTSTrolox;
    }

    public void setAntioxidantActivityABTSTrolox(Double antioxidantActivityABTSTrolox) {
        this.antioxidantActivityABTSTrolox = antioxidantActivityABTSTrolox;
    }

    @RDF("bdg:tpcGallicAcid")
    public Double getTpcGallicAcid() {
        return tpcGallicAcid;
    }

    public void setTpcGallicAcid(Double tpcGallicAcid) {
        this.tpcGallicAcid = tpcGallicAcid;
    }

    @RDF("bdg:tfcQuercetin")
    public Double getTfcQuercetin() {
        return tfcQuercetin;
    }

    public void setTfcQuercetin(Double tfcQuercetin) {
        this.tfcQuercetin = tfcQuercetin;
    }
}
