package com.agroknow.rdfization.gardian.semantics.json;

import java.util.ArrayList;

public class Sheet {
    private String sheetName;
    private ArrayList<Header> headerArrayList = new ArrayList<>();

    public Sheet(String sheetName, ArrayList<Header> headerArrayList) {
        this.sheetName = sheetName;
        this.headerArrayList = headerArrayList;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public ArrayList<Header> getHeaderArrayList() {
        return headerArrayList;
    }

    public void setHeaderArrayList(ArrayList<Header> headerArrayList) {
        this.headerArrayList = headerArrayList;
    }
}
