package ProdBuilder;

import Entity.Dimension;
import Entity.Location;
import Entity.Product;

public class ProductBuilderImpl implements ProductBuilder {
    Product p = new Product();

    public ProductBuilderImpl(String name, int amount) {
        p.setName(name);
        p.setAmount(amount);
    }

    @Override
    public ProductBuilder setLocation(Location l) {
        p.setLocation(l);
        return this;
    }

    @Override
    public ProductBuilder setDimension(Dimension d) {
        p.setDimension(d);
        return this;
    }

    @Override
    public Product build() {
        return p;
    }
}
