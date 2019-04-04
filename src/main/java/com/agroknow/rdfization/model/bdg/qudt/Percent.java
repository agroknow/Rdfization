package com.agroknow.rdfization.model.bdg.qudt;

import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;

@RDFBean("qudt:Percent")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/",
        "sosa = http://www.w3.org/ns/sosa/",
        "qudt = http://qudt.org/1.1/schema/qudt#"
})
public class Percent extends Unit{
}
