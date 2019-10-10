package com.agroknow.rdfization.model.geoclidean;

import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.cyberborean.rdfbeans.annotations.RDFSubject;

@RDFBean("bdg:satellite")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/",
        "geo = http://www.w3.org/2003/01/geo/wgs84_pos#",
        "qb = http://purl.org/linked-data/cube#",
        "rdfs = http://www.w3.org/2000/01/rdf-schema#"
})
public class BDGSatellite {

    private String name;

    public BDGSatellite() {
    }

    public BDGSatellite(String name) {
        this.name = name;
    }

    @RDFSubject(prefix = "bdg:satellite/")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
