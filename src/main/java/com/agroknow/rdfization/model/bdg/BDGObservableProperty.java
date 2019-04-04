package com.agroknow.rdfization.model.bdg;

import org.cyberborean.rdfbeans.annotations.RDF;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.cyberborean.rdfbeans.annotations.RDFSubject;

import java.util.Objects;
import java.util.UUID;

@RDFBean("sosa:ObservableProperty")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/",
        "rdfs = http://www.w3.org/2000/01/rdf-schema#"
})
public class BDGObservableProperty {

    private BDGSensor sensor;

    private String observes;

    public BDGObservableProperty() {
    }

    @RDFSubject(prefix = "http://dev.bigdatagrapes.eu/BDGObservableProperty/")
    public String getId() throws Exception{
        String source = this.toString();
        byte[] bytes = source.getBytes("UTF-8");
        UUID uuid = UUID.nameUUIDFromBytes(bytes);
        return uuid.toString();
    }

    @RDF("sosa:isObservedBy")
    public BDGSensor getSensor() {
        return sensor;
    }

    public void setSensor(BDGSensor sensor) {
        this.sensor = sensor;
    }

    @RDF("rdfs:label")
    public String getObserves() {
        return observes;
    }

    public void setObserves(String observes) {
        this.observes = observes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BDGObservableProperty property = (BDGObservableProperty) o;
        return Objects.equals(sensor, property.sensor) &&
                Objects.equals(observes, property.observes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensor, observes);
    }

    @Override
    public String toString() {
        return "BDGObservableProperty{" +
                "observes='" + observes + '\'' +
                '}';
    }
}
