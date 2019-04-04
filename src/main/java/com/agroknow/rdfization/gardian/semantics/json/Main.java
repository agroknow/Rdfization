package com.agroknow.rdfization.gardian.semantics.json;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        Model model = parser.getModel("C:\\Users\\Kyriakos\\Desktop\\ParserUpdate\\rdfJSON.json");

        Sheet sheet = model.getSheetArrayList().get(0);
        System.out.println("Sheet Name "+sheet.getSheetName());

        Header header = sheet.getHeaderArrayList().get(0);
        System.out.println(header.getHeaderName());
        System.out.println("class " + header.getClassOf());
        System.out.println(header.getData());
        System.out.println(header.isChecked());
    }
}
