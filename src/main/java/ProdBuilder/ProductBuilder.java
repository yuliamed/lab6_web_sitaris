package ProdBuilder;


import Entity.Dimension;
import Entity.Location;
import Entity.Product;

public interface ProductBuilder {
//    ProductBuilder setName(String name);
//    ProductBuilder setAmount(int amount);
    ProductBuilder setLocation(Location l);
    ProductBuilder setDimension(Dimension d);
    Product build();
}
