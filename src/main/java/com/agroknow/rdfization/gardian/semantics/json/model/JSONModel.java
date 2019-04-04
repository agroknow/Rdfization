package com.agroknow.rdfization.gardian.semantics.json.model;

import java.util.ArrayList;

public class JSONModel {
    private ArrayList<TabularSheet> sheets;

    public JSONModel(ArrayList<TabularSheet> sheets) {
        this.sheets = sheets;
    }

    public JSONModel() {
        this.sheets = new ArrayList<>();
    }

    public ArrayList<TabularSheet> getSheets() {
        return sheets;
    }

    public void setSheets(ArrayList<TabularSheet> sheets) {
        this.sheets = sheets;
    }
}
