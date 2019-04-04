package com.agroknow.rdfization.model.bdg;


import org.apache.jena.datatypes.xsd.impl.XSDDateTimeStampType;
import org.cyberborean.rdfbeans.annotations.RDF;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;

@RDFBean("time:Instant")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/",
        "time = http://www.w3.org/2006/time#"
})
@Deprecated
public class BDGObservationInstant {

    @RDF("time:inXSDDateTimeStamp")
    private XSDDateTimeStampType dateTimeStamp;

    public BDGObservationInstant() {
    }

    public XSDDateTimeStampType getDateTimeStamp() {
        return dateTimeStamp;
    }

    public void setDateTimeStamp(XSDDateTimeStampType dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }
}
