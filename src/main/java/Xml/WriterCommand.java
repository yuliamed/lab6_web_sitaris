package Xml;

import Entity.Product;

import java.util.ArrayList;

public class WriterCommand implements Command {
    XML xml;

    public WriterCommand(XML xml) {
        this.xml = xml;
    }

    @Override
    public void execute(ArrayList<Product> arr) {
        xml.writeToXML(arr);
    }
}
