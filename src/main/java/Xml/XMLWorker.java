package Xml;

import Entity.Product;

import java.util.ArrayList;

public class XMLWorker {
    Command writer;
    Command reader;

    public XMLWorker(Command writer, Command reader) {
        this.writer = writer;
        this.reader = reader;
    }

    public void writeXml(ArrayList<Product> arr){
        writer.execute(arr);
    }

    public void readXml(ArrayList<Product> arr){
        reader.execute(arr);
    }
}
