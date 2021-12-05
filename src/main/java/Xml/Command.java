package Xml;

import Entity.Product;

import java.util.ArrayList;

public interface Command {
    public void execute(ArrayList<Product> arr);
}
