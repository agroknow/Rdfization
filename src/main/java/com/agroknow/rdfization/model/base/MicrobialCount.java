package com.agroknow.rdfization.model.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.cyberborean.rdfbeans.annotations.RDFSubject;

@RDFBean("bdg:microbialCount")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/",
        "geo = http://www.w3.org/2003/01/geo/wgs84_pos#",
        "qb = http://purl.org/linked-data/cube#",
        "rdfs = http://www.w3.org/2000/01/rdf-schema#"
})
@JsonSerialize
public class MicrobialCount {

    private String value;

    public MicrobialCount() {
    }

    public MicrobialCount(String value) {

        if (!value.isEmpty()) {
            value = value.replace("<", "LT");
            value = value.replace("<=", "LTE");
            value = value.replace(">=", "GTE");
            value = value.replace(">", "GT");
        }

        this.value = value;
    }

    @RDFSubject(prefix = "http://dev.bigdatagrapes.eu/microbialCount/")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
