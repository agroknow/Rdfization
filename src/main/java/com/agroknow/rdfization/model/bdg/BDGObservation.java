package com.agroknow.rdfization.model.bdg;


import org.cyberborean.rdfbeans.annotations.RDF;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.cyberborean.rdfbeans.annotations.RDFSubject;

import java.util.Date;
import java.util.UUID;

@RDFBean("sosa:Observation")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/"
})
public class BDGObservation {

    private BDGSensor sensor;

    private BDGObservableProperty observableProperty;

    private Date resultTime;

    private BDGObservationResult result;

    public BDGObservation() {
    }

    @RDFSubject(prefix = "http://dev.bigdatagrapes.eu/BDGObservation/")
    public String getId() throws Exception {
        String source = this.toString();
        byte[] bytes = source.getBytes("UTF-8");
        UUID uuid = UUID.nameUUIDFromBytes(bytes);
        return uuid.toString();
    }

    @RDF("sosa:madeBySensor")
    public BDGSensor getSensor() {
        return sensor;
    }

    public void setSensor(BDGSensor sensor) {
        this.sensor = sensor;
    }

    @RDF("sosa:observedProperty")
    public BDGObservableProperty getObservableProperty() {
        return observableProperty;
    }

    public void setObservableProperty(BDGObservableProperty observableProperty) {
        this.observableProperty = observableProperty;
    }

    @RDF("sosa:resultTime")
    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

    @RDF("sosa:hasResult")
    public BDGObservationResult getResult() {
        return result;
    }

    public void setResult(BDGObservationResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BDGObservation{" +
                "observableProperty=" + observableProperty +
                ", resultTime=" + resultTime +
                ", result=" + result +
                '}';
    }
}
