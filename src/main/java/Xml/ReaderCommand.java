package Xml;


import Entity.Product;

import java.util.ArrayList;

public class ReaderCommand implements Command {
    XML xml;

    public ReaderCommand(XML xml) {
        this.xml = xml;
    }

    @Override
    public void execute(ArrayList<Product> arr) {
        xml.readProducts(arr);
    }
}
