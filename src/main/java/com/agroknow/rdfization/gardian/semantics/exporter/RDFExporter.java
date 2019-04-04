package com.agroknow.rdfization.gardian.semantics.exporter;

import com.agroknow.rdfization.gardian.semantics.model.DatasetModel;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDFS;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RDFExporter {
    private OntModel model;
    private String base;
    private String propertybase;
    private OntClass record;

    public RDFExporter() throws UnsupportedEncodingException {
        model = ModelFactory.createOntologyModel();
        //base = "http://dev.gardian.org/data/resource/";
        base = "http://dev.bigdatagrapes.eu/data/resource/";
        //propertybase = " http://dev.gardian.org#";
        propertybase = "http://dev.bigdatagrapes.eu#";
        record = model.createClass(propertybase + "Record");
        defineProperty("measurement_value");
        defineProperty("measurement_unit");
    }

    public Individual addRecord(Long current) {
        String fragment = current.toString();
//        String fragment = java.util.UUID.fromString(current.toString()).toString();
//        String fragment = java.util.UUID.randomUUID().toString();

        Individual newRecord = record.createIndividual(base + fragment);

        return newRecord;
    }

    public OntClass createClass(String fragment) {
        String encoded = fragment.trim();
//        String encoded = org.apache.jena.util.URIref.encode(fragment.trim());
        OntClass newClass = model.createClass(propertybase + encoded);

        return newClass;
    }

    public Resource createResource(String lexeme, Long current) {
        String fragment = current.toString();
//        String fragment = java.util.UUID.fromString(current.toString()).toString();
//        String fragment = java.util.UUID.randomUUID().toString();

        Resource newResource = model.createResource(base + fragment);
        newResource.addProperty(RDFS.label, lexeme);

        current++;
        return newResource;
    }

    public Resource createAnonymousResource() {
        Resource newResource = model.createResource();

        return newResource;
    }

    public void defineProperty(String fragment) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(fragment, "UTF-8");

        encoded = fragment.trim();
//        encoded = org.apache.jena.util.URIref.encode(encoded);
        Property newProperty = model.createProperty(propertybase + encoded);
    }

    public Property retrieveProperty(String fragment) {
        String encoded = fragment.trim();
//        encoded = org.apache.jena.util.URIref.encode(encoded);
        return model.getProperty(propertybase + encoded);
    }

    public void addProperty(Resource subject, String propertyFragment, String value) {

    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void export(DatasetModel dataset, Long current) {
        Model model = ModelFactory.createDefaultModel();
        //String base = "http://dev.gardian.org/data/resource/";
        String base = "http://dev.bigdatagrapes.eu/data/resource/";
        String fragment = current.toString();
//        String fragment = java.util.UUID.fromString(current.toString()).toString();
//        String fragment = java.util.UUID.randomUUID().toString();

        Resource record = model.createResource(base + fragment);
        current++;
    }

    public void export(String out) throws IOException {
        System.out.println("Write File");
        FileWriter writer = new FileWriter(out);
        model.setNsPrefix("bdg", "http://dev.bigdatagrapes.eu#");
        model.write(writer, "RDF/XML");
        writer.close();
    }
}
