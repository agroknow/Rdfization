package com.agroknow.rdfization.model.bdg;

import org.cyberborean.rdfbeans.annotations.RDF;
import org.cyberborean.rdfbeans.annotations.RDFBean;
import org.cyberborean.rdfbeans.annotations.RDFNamespaces;
import org.cyberborean.rdfbeans.annotations.RDFSubject;

@Deprecated
@RDFBean("bdg:BDGResource")
@RDFNamespaces({
        "bdg = http://dev.bigdatagrapes.eu/"
})
public class BDGResource {

    private String id;

    private String name;

    public BDGResource() {
    }

    @RDFSubject(prefix = "bdg:BDGResource/")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @RDF("foaf:name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
