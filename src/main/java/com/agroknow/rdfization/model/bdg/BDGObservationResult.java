package com.agroknow.rdfization.model.bdg;

import com.agroknow.rdfization.model.bdg.qudt.Unit;
import org.cyberborean.rdfbeans.annotations.RDF;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.cyberborean.rdfbeans.annotations.RDFSubject;

import java.util.UUID;

@RDFBean("qudt:QuantityValue")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/",
        "qudt = http://qudt.org/1.1/schema/qudt#"
})
public class BDGObservationResult {

    private Double numericValue;

    private Unit unit;

    public BDGObservationResult() {
    }

    @RDFSubject(prefix = "http://dev.bigdatagrapes.eu/BDGObservationResult/")
    public String getId() throws Exception {
        String source = this.toString();
        byte[] bytes = source.getBytes("UTF-8");
        UUID uuid = UUID.nameUUIDFromBytes(bytes);
        return uuid.toString();
    }

    @RDF("qudt:numericValue")
    public Double getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(Double numericValue) {
        this.numericValue = numericValue;
    }

    @RDF("qudt:unit")
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "BDGObservationResult{" +
                "numericValue=" + numericValue +
                ", unit=" + unit +
                '}';
    }
}
