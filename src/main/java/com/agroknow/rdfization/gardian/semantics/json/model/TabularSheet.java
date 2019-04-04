package com.agroknow.rdfization.gardian.semantics.json.model;

import java.util.ArrayList;

public class TabularSheet {
    private String name;
    private ArrayList<SheetHeader> headers;

    public TabularSheet(String name, ArrayList<SheetHeader> headers) {
        this.name = name;
        this.headers = headers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SheetHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(ArrayList<SheetHeader> headers) {
        this.headers = headers;
    }
}
