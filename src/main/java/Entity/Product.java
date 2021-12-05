package Entity;

public class Product {
    private String name;
    private int amount;
    private Location location;
    private Dimension dimension;

    @Override
    public String toString() {
        String s = "<-- Product " +
                "name= " + name +
                ", amount= " + amount;
        if(this.location!=null){
            s+=", row= " + this.location.getRow() + ", cell= " + this.location.getCell() + " -->";
        } else s+=" -->";
        return s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}

