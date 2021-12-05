package Entity;

public class Dimension {
    private double height;
    private double length;
    private double width;
    private double weight;

    public Dimension(double height, double length, double width, double weight) {
        if (weight >= 10000) {
            throw new IllegalArgumentException();
        } else {
            this.height = height;
            this.length = length;
            this.width = width;
            this.weight = weight;
        }
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "height=" + height +
                "\nlength=" + length +
                "\nwidth=" + width +
                "\nweight=" + weight;
    }
}
