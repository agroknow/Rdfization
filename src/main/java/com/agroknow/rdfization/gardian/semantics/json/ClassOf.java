package com.agroknow.rdfization.gardian.semantics.json;

import com.google.gson.JsonArray;

public class ClassOf {
    private String id;
    private String iri;
    private String short_form;
    private String obo_id;
    private String label;
    private String ontology_name;
    private String ontology_prefix;
    private String type;
    private boolean is_defining_ontology;
    private JsonArray description;

    public ClassOf(String id, String iri, String short_form, String obo_id, String label, String ontology_name, String ontology_prefix, String type, boolean is_defining_ontology, JsonArray description) {
        this.id = id;
        this.iri = iri;
        this.short_form = short_form;
        this.obo_id = obo_id;
        this.label = label;
        this.ontology_name = ontology_name;
        this.ontology_prefix = ontology_prefix;
        this.type = type;
        this.is_defining_ontology = is_defining_ontology;
        this.description = description;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getIri() {
        return iri;
    }

    public void setIri(String iri) {
        this.iri = iri;
    }

    public String getShort_form() {
        return short_form;
    }

    public void setShort_form(String short_form) {
        this.short_form = short_form;
    }

    public String getObo_id() {
        return obo_id;
    }

    public void setObo_id(String obo_id) {
        this.obo_id = obo_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOntology_name() {
        return ontology_name;
    }

    public void setOntology_name(String ontology_name) {
        this.ontology_name = ontology_name;
    }

    public String getOntology_prefix() {
        return ontology_prefix;
    }

    public void setOntology_prefix(String ontology_prefix) {
        this.ontology_prefix = ontology_prefix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIs_defining_ontology() {
        return is_defining_ontology;
    }

    public void setIs_defining_ontology(boolean is_defining_ontology) { this.is_defining_ontology = is_defining_ontology; }

    public JsonArray getDescription() {
        return description;
    }

    public void setDescription(JsonArray description) {
        this.description = description;
    }
}
