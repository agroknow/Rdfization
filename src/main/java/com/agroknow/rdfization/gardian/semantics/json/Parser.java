package com.agroknow.rdfization.gardian.semantics.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private String sheetName;
    private String headerName;
    private String field;
    private boolean editable;
    private String currentType;
    private boolean isNameValid;
    private boolean checked;
    private String type;
    private boolean validType;
    private boolean validFormat;
    private boolean validMeasure;
    private boolean validVoc;
    private String format;
    private String vocabulary;
    private JsonArray data;
    private String classOf_id;
    private String classOf_iri;
    private String classOf_shortForm;
    private String classOf_oboId;
    private String classOf_label;
    private String classOf_ontologyName;
    private String classOf_ontologyPrefix;
    private String classOf_type;
    private boolean classOf_isDefiningOntology;
    private JsonArray classOf_description;
    private String measure_id;
    private String measure_iri;
    private String measure_shortForm;
    private String measure_oboId;
    private String measure_label;
    private String measure_ontologyName;
    private String measure_ontologyPrefix;
    private String measure_type;
    private boolean measure_isDefiningOntology;
    private JsonArray measure_description;
    private ClassOf classOfTemp;
    private Measure measureTemp;
    private Header headerTemp;
    private Model modelTemp;
    private Sheet sheetTemp;
    private ArrayList<Header> headerArrayList = new ArrayList<>();

    public Model getModelByJSON(String jsonString) throws IOException {

        com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
        JsonObject input = parser.parse(jsonString).getAsJsonObject();

        JsonArray sheets = input.getAsJsonArray("sheets");
        ArrayList<Sheet> sheetArrayList = new ArrayList<>();


        for (int i = 0; i < sheets.size(); i++) {


            JsonObject sheet = sheets.get(i).getAsJsonObject();
            this.sheetName = sheet.get("sheetName").getAsString();
            JsonArray headers = sheet.getAsJsonArray("headers").getAsJsonArray();

            ArrayList<Header> headerArrayList = new ArrayList<>();

            for (int ii = 0; ii <= headers.size(); ii++) {
                try {
                    JsonObject header = headers.get(ii).getAsJsonObject();
                    try {
                        this.headerName = header.get("headerName").getAsString();
                    } catch (Exception ex) {
                        this.headerName = "null";
                    }
                    try {
                        this.field = header.get("field").getAsString();
                    } catch (Exception ex) {
                        this.field = "null";
                    }
                    try {
                        this.editable = header.get("editable").getAsBoolean();
                    } catch (Exception ex) {
                        this.editable = false;
                    }
                    try {
                        this.currentType = header.get("currentType").getAsString();
                    } catch (Exception ex) {
                        this.currentType = "null";
                    }
                    try {
                        this.isNameValid = header.get("isNameValid").getAsBoolean();
                    } catch (Exception ex) {
                        this.isNameValid = false;
                    }
                    try {
                        this.checked = header.get("checked").getAsBoolean();
                    } catch (Exception ex) {
                        this.checked = false;
                    }
                    try {
                        this.type = header.get("type").getAsString();
                    } catch (Exception ex) {
                        this.type = "null";
                    }
                    try {
                        this.validType = header.get("validType").getAsBoolean();
                    } catch (Exception ex) {
                        this.validType = false;
                    }
                    try {
                        this.validFormat = header.get("validFormat").getAsBoolean();
                    } catch (Exception ex) {
                        this.validFormat = false;
                    }
                    try {
                        this.validMeasure = header.get("validMeasure").getAsBoolean();
                    } catch (Exception ex) {
                        this.validMeasure = false;
                    }
                    try {
                        this.validVoc = header.get("validVoc").getAsBoolean();
                    } catch (Exception ex) {
                        this.validVoc = false;
                    }
                    try {
                        this.format = header.get("format").getAsString();
                    } catch (Exception ex) {
                        this.format = "null";
                    }
                    try {
                        this.vocabulary = header.get("vocabulary").getAsString();
                    } catch (Exception ex) {
                        this.vocabulary = "null";
                    }
                    try {
                        this.data = header.get("data").getAsJsonArray();
                    } catch (Exception ex) {
                        System.out.println("Could not read data");
                        this.data = null;
                    }
                    try {
                        JsonObject classOf = header.getAsJsonObject("classOf");
                        try {
                            this.classOf_id = classOf.get("id").getAsString();
                        } catch (Exception ex) {
                            this.classOf_id = "null";
                        }
                        try {
                            this.classOf_iri = classOf.get("iri").getAsString();
                        } catch (Exception ex) {
                            this.classOf_iri = "null";
                        }
                        try {
                            this.classOf_shortForm = classOf.get("short_form").getAsString();
                        } catch (Exception ex) {
                            this.classOf_shortForm = "null";
                        }
                        try {
                            this.classOf_oboId = classOf.get("obo_id").getAsString();
                        } catch (Exception ex) {
                            this.classOf_oboId = "null";
                        }
                        try {
                            this.classOf_label = classOf.get("label").getAsString();
                        } catch (Exception ex) {
                            this.classOf_label = "null";
                        }
                        try {
                            this.classOf_ontologyName = classOf.get("ontology_name").getAsString();
                        } catch (Exception ex) {
                            this.classOf_ontologyName = "null";
                        }
                        try {
                            this.classOf_ontologyPrefix = classOf.get("ontology_prefix").getAsString();
                        } catch (Exception ex) {
                            this.classOf_ontologyPrefix = "null";
                        }
                        try {
                            this.classOf_type = classOf.get("type").getAsString();
                        } catch (Exception ex) {
                            this.classOf_type = "null";
                        }
                        try {
                            this.classOf_isDefiningOntology = classOf.get("is_defining_ontology").getAsBoolean();
                        } catch (Exception ex) {
                            this.classOf_isDefiningOntology = false;
                        }
                        try {
                            this.classOf_description = classOf.get("description").getAsJsonArray();
                        } catch (Exception ex) {
                            this.classOf_description = null;
                        }
                        try {
                            this.data = header.get("data").getAsJsonArray();
                        } catch (Exception ex) {
                            System.out.println("Could not read data");
                            this.data = null;
                        }

                        this.classOfTemp = new ClassOf(this.classOf_id, this.classOf_iri, this.classOf_shortForm, this.classOf_oboId, this.classOf_label, this.classOf_ontologyName, this.classOf_ontologyPrefix, this.classOf_type, this.classOf_isDefiningOntology, this.classOf_description);


                    } catch (Exception ex) {
                        this.classOfTemp = null;
                    }
                    try {
                        JsonObject measure = header.getAsJsonObject("measure");
                        try {
                            this.measure_id = measure.get("id").getAsString();
                        } catch (Exception ex) {
                            this.measure_id = "null";
                        }
                        try {
                            this.measure_iri = measure.get("iri").getAsString();
                        } catch (Exception ex) {
                            this.measure_iri = "null";
                        }
                        try {
                            this.measure_shortForm = measure.get("short_form").getAsString();
                        } catch (Exception ex) {
                            this.measure_shortForm = "null";
                        }
                        try {
                            this.measure_oboId = measure.get("obo_id").getAsString();
                        } catch (Exception ex) {
                            this.measure_oboId = "null";
                        }
                        try {
                            this.measure_label = measure.get("label").getAsString();
                        } catch (Exception ex) {
                            this.measure_label = "null";
                        }
                        try {
                            this.measure_ontologyName = measure.get("ontology_name").getAsString();
                        } catch (Exception ex) {
                            this.measure_ontologyName = "null";
                        }
                        try {
                            this.measure_ontologyPrefix = measure.get("ontology_prefix").getAsString();
                        } catch (Exception ex) {
                            this.measure_ontologyPrefix = "null";
                        }
                        try {
                            this.measure_type = measure.get("type").getAsString();
                        } catch (Exception ex) {
                            this.measure_type = "null";
                        }
                        try {
                            this.measure_isDefiningOntology = measure.get("is_defining_ontology").getAsBoolean();
                        } catch (Exception ex) {
                            this.measure_isDefiningOntology = false;
                        }
                        try {
                            this.measure_description = measure.get("description").getAsJsonArray();
                        } catch (Exception ex) {
                            this.measure_description = null;
                        }
                        try {
                            this.data = header.get("data").getAsJsonArray();
                        } catch (Exception ex) {
                            System.out.println("Could not read data");
                            this.data = null;
                        }

                        this.measureTemp = new Measure(this.measure_id, this.measure_iri, this.measure_shortForm, this.measure_oboId, this.measure_label, this.measure_ontologyName, this.measure_ontologyPrefix, this.measure_type, this.measure_isDefiningOntology, this.measure_description);


                    } catch (Exception ex) {
                        this.classOfTemp = null;
                    }


                    this.headerTemp = new Header(this.headerName, this.field, this.editable, this.currentType, this.isNameValid, this.checked, this.type, this.validType, this.validFormat, this.validMeasure, this.validVoc, this.format, this.vocabulary, this.classOfTemp, this.measureTemp, this.data);
                    headerArrayList.add(headerTemp);

                } catch (Exception ex) {
//                    this.headerTemp = null;
                }


            }
            this.sheetTemp = new Sheet(this.sheetName,headerArrayList);
            sheetArrayList.add(this.sheetTemp);

        }
        this.modelTemp= new Model(sheetArrayList);

        return this.modelTemp;

    }

    public Model getModel(String absolutPathOfJsonFile) throws IOException {
        Parser parser = new Parser();
        JsonObject input = parser.read(absolutPathOfJsonFile);

        JsonArray sheets = input.getAsJsonArray("sheets");
            ArrayList<Sheet> sheetArrayList = new ArrayList<>();


        for (int i = 0; i < sheets.size(); i++) {


            JsonObject sheet = sheets.get(i).getAsJsonObject();
            this.sheetName = sheet.get("sheetName").getAsString();
            JsonArray headers = sheet.getAsJsonArray("headers").getAsJsonArray();

            ArrayList<Header> headerArrayList = new ArrayList<>();

            for (int ii = 0; ii <= headers.size(); ii++) {
                try {
                    JsonObject header = headers.get(ii).getAsJsonObject();
                    try {
                        this.headerName = header.get("headerName").getAsString();
                    } catch (Exception ex) {
                        this.headerName = "null";
                    }
                    try {
                        this.field = header.get("field").getAsString();
                    } catch (Exception ex) {
                        this.field = "null";
                    }
                    try {
                        this.editable = header.get("editable").getAsBoolean();
                    } catch (Exception ex) {
                        this.editable = false;
                    }
                    try {
                        this.currentType = header.get("currentType").getAsString();
                    } catch (Exception ex) {
                        this.currentType = "null";
                    }
                    try {
                        this.isNameValid = header.get("isNameValid").getAsBoolean();
                    } catch (Exception ex) {
                        this.isNameValid = false;
                    }
                    try {
                        this.checked = header.get("checked").getAsBoolean();
                    } catch (Exception ex) {
                        this.checked = false;
                    }
                    try {
                        this.type = header.get("type").getAsString();
                    } catch (Exception ex) {
                        this.type = "null";
                    }
                    try {
                        this.validType = header.get("validType").getAsBoolean();
                    } catch (Exception ex) {
                        this.validType = false;
                    }
                    try {
                        this.validFormat = header.get("validFormat").getAsBoolean();
                    } catch (Exception ex) {
                        this.validFormat = false;
                    }
                    try {
                        this.validMeasure = header.get("validMeasure").getAsBoolean();
                    } catch (Exception ex) {
                        this.validMeasure = false;
                    }
                    try {
                        this.validVoc = header.get("validVoc").getAsBoolean();
                    } catch (Exception ex) {
                        this.validVoc = false;
                    }
                    try {
                        this.format = header.get("format").getAsString();
                    } catch (Exception ex) {
                        this.format = "null";
                    }
                    try {
                        this.vocabulary = header.get("vocabulary").getAsString();
                    } catch (Exception ex) {
                        this.vocabulary = "null";
                    }
                    try {
                        this.data = header.get("data").getAsJsonArray();
                    } catch (Exception ex) {
                        System.out.println("Could not read data");
                        this.data = null;
                    }
                    try {
                        JsonObject classOf = header.getAsJsonObject("classOf");
                        try {
                            this.classOf_id = classOf.get("id").getAsString();
                        } catch (Exception ex) {
                            this.classOf_id = "null";
                        }
                        try {
                            this.classOf_iri = classOf.get("iri").getAsString();
                        } catch (Exception ex) {
                            this.classOf_iri = "null";
                        }
                        try {
                            this.classOf_shortForm = classOf.get("short_form").getAsString();
                        } catch (Exception ex) {
                            this.classOf_shortForm = "null";
                        }
                        try {
                            this.classOf_oboId = classOf.get("obo_id").getAsString();
                        } catch (Exception ex) {
                            this.classOf_oboId = "null";
                        }
                        try {
                            this.classOf_label = classOf.get("label").getAsString();
                        } catch (Exception ex) {
                            this.classOf_label = "null";
                        }
                        try {
                            this.classOf_ontologyName = classOf.get("ontology_name").getAsString();
                        } catch (Exception ex) {
                            this.classOf_ontologyName = "null";
                        }
                        try {
                            this.classOf_ontologyPrefix = classOf.get("ontology_prefix").getAsString();
                        } catch (Exception ex) {
                            this.classOf_ontologyPrefix = "null";
                        }
                        try {
                            this.classOf_type = classOf.get("type").getAsString();
                        } catch (Exception ex) {
                            this.classOf_type = "null";
                        }
                        try {
                            this.classOf_isDefiningOntology = classOf.get("is_defining_ontology").getAsBoolean();
                        } catch (Exception ex) {
                            this.classOf_isDefiningOntology = false;
                        }
                        try {
                            this.classOf_description = classOf.get("description").getAsJsonArray();
                        } catch (Exception ex) {
                            this.classOf_description = null;
                        }
                        try {
                            this.data = header.get("data").getAsJsonArray();
                        } catch (Exception ex) {
                            System.out.println("Could not read data");
                            this.data = null;
                        }

                        this.classOfTemp = new ClassOf(this.classOf_id, this.classOf_iri, this.classOf_shortForm, this.classOf_oboId, this.classOf_label, this.classOf_ontologyName, this.classOf_ontologyPrefix, this.classOf_type, this.classOf_isDefiningOntology, this.classOf_description);


                    } catch (Exception ex) {
                        this.classOfTemp = null;
                    }
                    try {
                        JsonObject measure = header.getAsJsonObject("measure");
                        try {
                            this.measure_id = measure.get("id").getAsString();
                        } catch (Exception ex) {
                            this.measure_id = "null";
                        }
                        try {
                            this.measure_iri = measure.get("iri").getAsString();
                        } catch (Exception ex) {
                            this.measure_iri = "null";
                        }
                        try {
                            this.measure_shortForm = measure.get("short_form").getAsString();
                        } catch (Exception ex) {
                            this.measure_shortForm = "null";
                        }
                        try {
                            this.measure_oboId = measure.get("obo_id").getAsString();
                        } catch (Exception ex) {
                            this.measure_oboId = "null";
                        }
                        try {
                            this.measure_label = measure.get("label").getAsString();
                        } catch (Exception ex) {
                            this.measure_label = "null";
                        }
                        try {
                            this.measure_ontologyName = measure.get("ontology_name").getAsString();
                        } catch (Exception ex) {
                            this.measure_ontologyName = "null";
                        }
                        try {
                            this.measure_ontologyPrefix = measure.get("ontology_prefix").getAsString();
                        } catch (Exception ex) {
                            this.measure_ontologyPrefix = "null";
                        }
                        try {
                            this.measure_type = measure.get("type").getAsString();
                        } catch (Exception ex) {
                            this.measure_type = "null";
                        }
                        try {
                            this.measure_isDefiningOntology = measure.get("is_defining_ontology").getAsBoolean();
                        } catch (Exception ex) {
                            this.measure_isDefiningOntology = false;
                        }
                        try {
                            this.measure_description = measure.get("description").getAsJsonArray();
                        } catch (Exception ex) {
                            this.measure_description = null;
                        }
                        try {
                            this.data = header.get("data").getAsJsonArray();
                        } catch (Exception ex) {
                            System.out.println("Could not read data");
                            this.data = null;
                        }

                        this.measureTemp = new Measure(this.measure_id, this.measure_iri, this.measure_shortForm, this.measure_oboId, this.measure_label, this.measure_ontologyName, this.measure_ontologyPrefix, this.measure_type, this.measure_isDefiningOntology, this.measure_description);


                    } catch (Exception ex) {
                        this.classOfTemp = null;
                    }


                    this.headerTemp = new Header(this.headerName, this.field, this.editable, this.currentType, this.isNameValid, this.checked, this.type, this.validType, this.validFormat, this.validMeasure, this.validVoc, this.format, this.vocabulary, this.classOfTemp, this.measureTemp, this.data);
                    headerArrayList.add(headerTemp);

                } catch (Exception ex) {
//                    this.headerTemp = null;
                }


            }
            this.sheetTemp = new Sheet(this.sheetName,headerArrayList);
            sheetArrayList.add(this.sheetTemp);

        }
        this.modelTemp= new Model(sheetArrayList);

        return this.modelTemp;

    }

    public JsonObject read(String absolutePathToRead) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(absolutePathToRead));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        reader.close();
        String content = stringBuilder.toString();
        com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
        JsonObject jsonObject = parser.parse(content).getAsJsonObject();

        return jsonObject;
    }
}
