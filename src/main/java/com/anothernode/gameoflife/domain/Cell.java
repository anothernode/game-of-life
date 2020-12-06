package com.anothernode.gameoflife.domain;

import static java.text.MessageFormat.format;

public class Cell implements Comparable<Cell> {

    private Location location;

    public Cell() {}

    public Cell(Location location) {
        this.location = location;
    }

    public Cell(int x, int y) {
        this.location = Location.create(x, y);
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Cell)) {
            return false;
        } else {
            return this.location.equals(((Cell) that).getLocation());
        }
    }

    @Override
    public int hashCode() {
        return this.getLocation().hashCode();
    }

    @Override
    public String toString() {
        return format("Cell[x = {0}, y = {1}]",
                getLocation().getX(),
                getLocation().getY());
    }

    @Override
    public int compareTo(Cell that) {
        if (this.equals(that)) return 0;
        if (this.location.getY() < that.location.getY()) return -1;
        if (this.location.getY() > that.location.getY()) return 1;
        if (this.location.getX() < that.location.getX()) return -1;
        if (this.location.getX() > that.location.getX()) return 1;
        return 0;
    }
}
