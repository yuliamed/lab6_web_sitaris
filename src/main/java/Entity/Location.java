package Entity;

public class Location {
    private int floor;
    private int row;
    private char cell;

    public Location(int row, int floor, char cell) {
        this.floor = floor;
        this.row = row;
        this.cell = cell;
    }

    public int getFloor() {
        return floor;
    }

    public int getRow() {
        return row;
    }

    public char getCell() {
        return cell;
    }

    @Override
    public String toString() {
        return  "R" + row +"F" + floor +
                cell;
    }
}
