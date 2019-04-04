package com.agroknow.rdfization.gardian.semantics.parser;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

@Component
public class OntologyHandler {

    @Value("${agrovoc.repo}")
    private String test;

    private static String BASE_DIR;
    private OntModel model;
    private String ontologyFile;

    public OntologyHandler() {
    }

    public OntologyHandler(String filename){
        this.ontologyFile = filename;

        try {
            String resourceName = "configuration.properties"; // could also be a constant
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Properties props = new Properties();
            try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
                props.load(resourceStream);
            }
            BASE_DIR = props.getProperty("ontology_directory");
        } catch (Exception ignored) {
        }
        this.loadModel(BASE_DIR + File.separator + ontologyFile + ".owl");
    }

    public void loadModel(String filename) {
        model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
        model.read(filename);
    }

    public String getOntologyFile() {
        return ontologyFile;
    }

    public void setOntologyFile(String ontologyFile) {
        this.ontologyFile = ontologyFile;
    }

    public OntClass findClass(String uri){
        OntClass concept = model.getOntClass(uri);
        //System.out.println(concept.getLocalName());
        return concept;
    }

    public void getInstances(OntClass concept){
        ExtendedIterator<OntResource> individuals = (ExtendedIterator<OntResource>) concept.listInstances();
        List<OntResource> instances = individuals.toList();
        for (OntResource instance : instances) System.out.println(instance.toString());
    }

    public List<OntClass> getsubclasses(OntClass concept){
        ExtendedIterator<OntClass> subs = concept.listSubClasses();
        List<OntClass> subclasses = subs.toList();

        return subclasses;
    }

    public String findMatchInSubclasses(OntClass concept, String lexeme){
        List<OntClass> subclasses = this.getsubclasses(concept);
        for (OntClass subclass : subclasses){

        }
        return null;
    }

    public String findMatchInInstances(OntClass concept, String lexeme){
        return null;
    }

    public String getLabel(OntClass concept, String preferredLanguage) {
        List<RDFNode> labels = concept.listPropertyValues(RDFS.label).toList();
        if (!labels.isEmpty()){
            return labels.get(0).toString();
        }
        return null;
    }

    public OntClass match(int flag, String lexeme, List<OntClass> concepts){
        for (OntClass concept : concepts){
            if (concept.getLocalName().toLowerCase().equals(lexeme.toLowerCase())) return concept;
            if (inLabels(concept, lexeme)) {
                return concept;
            }
            if (inProperties(concept, lexeme)) {
                return concept;
            }
        }

        return null;
    }

    private boolean inLabels(OntClass concept, String lexeme) {
        List<RDFNode> labels = concept.listPropertyValues(RDFS.label).toList();
        for (RDFNode label : labels){
            if (label.toString().toLowerCase().equals(lexeme.toLowerCase()))
                return true;
        }
        return false;
    }

    private boolean inProperties(OntClass concept, String lexeme) {
        List<OntProperty> properties = concept.listDeclaredProperties().toList();
        for (OntProperty property : properties) {
            List<RDFNode> values = concept.listPropertyValues(property).toList();
            for (RDFNode value : values)
                if (value.toString().toLowerCase().equals(lexeme.toLowerCase()))
                    return true;
        }
        return false;
    }

    private void showLabel(OntClass concept){
        System.out.println("Concept labels:");
        List<RDFNode> labels = concept.listPropertyValues(RDFS.label).toList();
        for (RDFNode label : labels){
            System.out.println(label.toString());
        }
        System.out.println("-------------------");
    }
}
