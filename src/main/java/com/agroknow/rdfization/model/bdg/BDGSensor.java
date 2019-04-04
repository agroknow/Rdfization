package com.agroknow.rdfization.model.bdg;

import org.cyberborean.rdfbeans.annotations.RDF;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFContainer;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.cyberborean.rdfbeans.annotations.RDFSubject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RDFBean("sosa:Sensor")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/",
        "geo = http://www.w3.org/2003/01/geo/wgs84_pos#",
        "rdfs = http://www.w3.org/2000/01/rdf-schema#"
})
public class BDGSensor {

    private String name;

    private Collection<BDGObservation> observations = new ArrayList<>();

    private Collection<BDGObservableProperty> properties = new ArrayList<>();

    private Double latitude;

    private Double longitude;

    public BDGSensor() {
    }

    @RDFSubject(prefix = "http://dev.bigdatagrapes.eu/BSGSensor/")
    public String getId() throws Exception{
        String source = this.toString();
        byte[] bytes = source.getBytes("UTF-8");
        UUID uuid = UUID.nameUUIDFromBytes(bytes);
        return uuid.toString();
    }

    @RDF("rdfs:label")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Deprecated
    @RDF("sosa:makeObservation")
    public Collection<BDGObservation> getObservations() {
        return observations;
    }

    public void setObservations(List<BDGObservation> observations) {
        this.observations = observations;
    }

    @RDF("geo:lat")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @RDF("geo:long")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Deprecated
    @RDF("sosa:observes")
    public Collection<BDGObservableProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<BDGObservableProperty> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "BDGSensor{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
