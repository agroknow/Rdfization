package com.agroknow.rdfization.gardian.semantics.importer;

import com.agroknow.rdfization.gardian.semantics.exporter.RDFExporter;
import com.agroknow.rdfization.gardian.semantics.json.Header;
import com.agroknow.rdfization.gardian.semantics.json.Measure;
import com.agroknow.rdfization.gardian.semantics.json.Model;
import com.agroknow.rdfization.gardian.semantics.json.Parser;
import com.agroknow.rdfization.gardian.semantics.json.Sheet;
import com.agroknow.rdfization.gardian.semantics.parser.OntologyHandler;
import com.agroknow.rdfization.model.RdfizationRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.rdf.model.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class JSONImporter {
    private Model jsonModel;

    private ArrayList<Sheet> sheets;
    private ArrayList<Header> validHeaders;
    private ArrayList<Header> concepts;
    private ArrayList<Header> measures;
    private ArrayList<String> ontologies;

    private ArrayList<OntologyHandler> handlers;
    private ArrayList<Individual> records;

    private RDFExporter exporter;
    private static String OUTPUT_DIR;
    private String outputFilename;
    private String fullPath;

    public JSONImporter(String content, boolean isfile, RdfizationRequest request, Long currentMax) throws IOException {
        //this.filename = file;

        System.out.println("Importer Constructor");

        validHeaders = new ArrayList<>();
        sheets = new ArrayList<>();
        concepts = new ArrayList<>();
        measures = new ArrayList<>();
        ontologies = new ArrayList<>();
        handlers = new ArrayList<>();
        records = new ArrayList<>();

        exporter = new RDFExporter();

        outputFilename = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), "rdf");


        if (isfile == true) {
            Parser jsonParser = new Parser();
            jsonModel = jsonParser.getModel(content);
        } else if (isfile != true) {
            Parser jsonParser = new Parser();
            jsonModel = jsonParser.getModelByJSON(content);
        }

        sheets = jsonModel.getSheetArrayList();

        for (Sheet sheet : sheets) {
            request.setNoRecords(request.getNoRecords() + sheet.getHeaderArrayList().stream().mapToLong(h -> h.getData().size()).sum());
            resolve(sheet, currentMax);
            reset();
        }
    }


    public void resolve(Sheet sheet, Long current) throws IOException {
        resolveHeaders(sheet);
        resolveTypes();
        locateOntologiesByName();

        constructRecords(current);
        constructProperties();

        constructConcepts(current);
        constructScalars();

        try {
            String resourceName = "configuration.properties"; // could also be a constant
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Properties props = new Properties();
            try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
                props.load(resourceStream);
            }
            OUTPUT_DIR = props.getProperty("export_directory");
        } catch (Exception ignored) {
        }
//
//        ResourceBundle bundle = ResourceBundle.getBundle("configuration");
//        OUTPUT_DIR  = bundle.getString("export_directory");

        System.out.println("Transformation Completed");
        fullPath = OUTPUT_DIR + File.separator + outputFilename;
        exporter.export(fullPath);

    }

    public void resolveHeaders(Sheet sheet) {
        ArrayList<Header> headers = sheet.getHeaderArrayList();
        for (Header header : headers) {
            if (header != null && header.isChecked()) {
                System.out.println(header.getHeaderName() + ": " + header.getCurrentType());
                validHeaders.add(header);
            }
        }
    }

    public void resolveTypes() {
        for (Header header : validHeaders) {
            if (header.getCurrentType().equals("Class")) {
                concepts.add(header);
            } else if (header.getCurrentType().equals("Manual")) {
                measures.add(header);
            }
        }
    }

    public void reset() {
        validHeaders.clear();
        concepts.clear();
        measures.clear();
    }

    public void locateOntologiesByIRI() {
        for (Header concept : concepts) {
            String ontologyIRI = concept.getClassOf().getIri();
            String ontologyFilename = locateOntologyFile(ontologyIRI);
            if (!ontologies.contains(ontologyFilename)) {
                ontologies.add(ontologyFilename);
                handlers.add(new OntologyHandler(ontologyFilename));
            }
        }
    }

    public void locateOntologiesByName() {
        for (Header concept : concepts) {
            String ontologyName = concept.getClassOf().getOntology_name();
            if (!ontologies.contains(ontologyName)) {
                ontologies.add(ontologyName);
                handlers.add(new OntologyHandler(ontologyName));
            }
        }
    }

    private boolean isLoaded(String ontology) {
        for (OntologyHandler handler : handlers) {
            if (handler.getOntologyFile().equals(ontology))
                return true;
        }
        return false;
    }

    public String locateOntologyFile(String iri) {
        //TODO index for iri-local match
        return null;
    }

    public void constructProperties() throws UnsupportedEncodingException {
        for (Header header : validHeaders) {
            String name = header.getHeaderName();
            exporter.defineProperty(name);
        }
    }

    public void constructRecords(Long current) {
        int datasetSize = validHeaders.get(0).getData().size();
        for (int i = 0; i < datasetSize; i++) {
            records.add(exporter.addRecord(current));
            current++;
        }
    }

    public void constructConcepts(Long current) {
        for (Resource record : records) {
            int pos = records.indexOf(record);

            for (Header concept : concepts) {
                //System.out.println("searching for ontology: " + concept.getClassOf().getOntology_name());
                OntologyHandler handler = loadHandler(concept.getClassOf().getOntology_name());
                if (handler == null)
                    System.out.println("Did not find handler");
                String data = "";

                try {
                    data = concept.getData().get(pos).getAsString();
                } catch (java.lang.UnsupportedOperationException ignored) {

                }
                if ("".equals(data)) {
                    continue;
                }

                OntClass currentConcept = handler.findClass(concept.getClassOf().getIri());
                List<OntClass> subclasses = handler.getsubclasses(currentConcept);
                OntClass match = handler.match(0, data, subclasses);
                if (match != null) {
                    record.addProperty(exporter.retrieveProperty(concept.getHeaderName()), match);
                } else {
                    OntClass newClass = exporter.createClass(data);
                    newClass.setSuperClass(currentConcept);
                    record.addProperty(exporter.retrieveProperty(concept.getHeaderName()), newClass);
                }
                current++;
            }
        }
    }

    public void constructScalars() {
        for (Resource record : records) {
            int pos = records.indexOf(record);
            for (Header scalar : measures) {
                System.out.println("Scalar is: " + scalar.getHeaderName());
                Resource measurement = exporter.createAnonymousResource();
                XSDDatatype type = resolveDatatype(scalar.getType());
                String data = scalar.getData().get(pos).getAsString();
                if (type.equals(XSDDatatype.XSDstring))
                    record.addProperty(exporter.retrieveProperty(scalar.getHeaderName()), data, type);
                else if (type.equals(XSDDatatype.XSDdate)) {
                    data = normaliseDate(data, scalar.getFormat());
                    record.addProperty(exporter.retrieveProperty(scalar.getHeaderName()), data, type);
                } else {
                    OntClass unit = resolveUnit(scalar.getMeasure());
                    if (unit != null) {
                        measurement.addProperty(exporter.retrieveProperty("measurement_value"), data, type);
                        measurement.addProperty(exporter.retrieveProperty("measurement_unit"), unit);
                    } else
                        measurement.addProperty(exporter.retrieveProperty("measurement_value"), data, type);
                    record.addProperty(exporter.retrieveProperty(scalar.getHeaderName()), measurement);
                }
            }
        }
    }

    public OntClass resolveUnit(Measure measure) {
        if (measure == null)
            return null;
        else
            return exporter.createClass(measure.getIri());
    }

    private String normaliseDate(String date, String format) {
        //TODO logic for converting supported date formats to normative form
        String year = "";
        String month = "";
        String day = "";
        System.out.println(format);
        switch (format) {
            case "dd/mm/yy":
            case "dd/mm/yyyy":
            case "d/m/yy":
            case "d/m/yyyy":
                year = date.substring(date.lastIndexOf("/") + 1);
                month = date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"));
                day = date.substring(0, date.indexOf("/"));
                break;
            case "dd-mmm-yy":
            case "dd-mmm-yyyy":
                year = date.substring(date.lastIndexOf("-") + 1);
                month = date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"));
                day = date.substring(0, date.indexOf("-"));
                break;
            case "ddmmyy":
                year = date.substring(4, 6);
                month = date.substring(2, 4);
                day = date.substring(0, 2);
                break;
            case "ddmmmyy":
                year = date.substring(5, 7);
                month = date.substring(2, 5);
                day = date.substring(0, 2);
                break;
            case "ddmmmyyyy":
                year = date.substring(5, 9);
                month = date.substring(2, 5);
                day = date.substring(0, 2);
                break;
            case "dmmmyy":
                year = date.substring(4, 6);
                month = date.substring(1, 4);
                day = date.substring(0, 1);
                break;
            case "yymmdd":
                year = date.substring(0, 2);
                month = date.substring(2, 4);
                day = date.substring(4, 6);
                break;
            case "yy/mm/dd":
                year = date.substring(0, 2);
                month = date.substring(3, 5);
                day = date.substring(6, 8);
                break;
            case "yyyymmdd":
                year = date.substring(0, 4);
                month = date.substring(4, 6);
                day = date.substring(6, 8);
                break;
            case "mmddyy":
                year = date.substring(4, 6);
                month = date.substring(0, 2);
                day = date.substring(2, 4);
                break;
            case "ddmmyyyy":
                year = date.substring(4, 8);
                month = date.substring(2, 4);
                day = date.substring(0, 2);
                break;
            case "mmddyyyy":
                year = date.substring(4, 8);
                month = date.substring(0, 2);
                day = date.substring(2, 4);
                break;

            case "yyyy/mm/dd":
                year = date.substring(0, 4);
                month = date.substring(5, 7);
                day = date.substring(8, 10);
                break;

            case "mm/dd/yy":
                year = date.substring(6, 8);
                month = date.substring(0, 2);
                day = date.substring(3, 5);
                break;
            case "mm/dd/yyyy":
                year = date.substring(6, 10);
                month = date.substring(0, 2);
                day = date.substring(3, 5);
                break;

            case "mmm-dd-yy":
                year = date.substring(7, 9);
                month = date.substring(0, 3);
                day = date.substring(4, 6);
                break;
            case "mmm-dd-yyyy":
                year = date.substring(7, 11);
                month = date.substring(0, 3);
                day = date.substring(4, 6);
                break;
        }
        //System.out.println("year:" + year + "month: " + month + "day: " + day);
        String response = formatYear(year) + "-" + formatMonth(month) + "-" + formatDay(day);
        //System.out.println(response);
        return response;
    }

    private String formatDay(String day) {
        return String.format("%02d", Integer.parseInt(day));
    }

    private String formatYear(String year) {
        String formattedYear = "";

        int numeric = Integer.parseInt(year);

        if (numeric < 100) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (currentYear - 2000 < numeric) formattedYear = "19" + year;
            else formattedYear = "20" + year;
        } else formattedYear = year.toString();

        return formattedYear;
    }

    private String formatMonth(String month) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        System.out.println("month: " + month);
        int numeric = Arrays.asList(months).indexOf(month) + 1;
        if (numeric == 0)
            return String.format("%02d", Integer.parseInt(month));
        return String.format("%02d", numeric);
    }

    public XSDDatatype resolveDatatype(String type) {
        switch (type) {
            case "integer":
                return XSDDatatype.XSDinteger;
            case "decimal":
                return XSDDatatype.XSDdecimal;
        }
        return XSDDatatype.XSDstring;
    }

    private OntologyHandler loadHandler(String ontology) {
        for (OntologyHandler handler : handlers) {
            if (handler.getOntologyFile().equals(ontology))
                return handler;
        }
        return null;
    }

    public void info() {
        System.out.println(validHeaders.size() + " columns found");
        System.out.println(concepts.size() + " class columns");
        System.out.println(measures.size() + " scalar columns");
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public void setOutputFilename(String outputFilename) {
        this.outputFilename = outputFilename;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}
