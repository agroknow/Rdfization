package com.agroknow.rdfization.model.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.cyberborean.rdfbeans.annotations.RDFSubject;

@RDFBean("qb:dataset")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/",
        "geo = http://www.w3.org/2003/01/geo/wgs84_pos#",
        "qb = http://purl.org/linked-data/cube#",
        "rdfs = http://www.w3.org/2000/01/rdf-schema#"
})
@JsonSerialize
public class Dataset {

    private String name;

    public Dataset() {
    }

    public Dataset(String name) {
        this.name = name;
    }

    @RDFSubject(prefix = "http://dev.bigdatagrapes.eu/data/")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
