package com.agroknow.rdfization.gardian.semantics.json;

import java.util.ArrayList;

public class Model {

   private ArrayList<Sheet> sheetArrayList = new ArrayList<>();

   public Model(ArrayList<Sheet> sheetArrayList) {
        this.sheetArrayList = sheetArrayList;
   }

   public ArrayList<Sheet> getSheetArrayList() {
       return sheetArrayList;
   }

   public void setSheetArrayList(ArrayList<Sheet> sheetArrayList) {
        this.sheetArrayList = sheetArrayList;
    }
}
