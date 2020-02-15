package com.anothernode.gameoflife;

public class Cell {

    private Location location;

    public Cell(Location location) {
        this.location = location;
    }

    public Cell(int x, int y) {
        this.location = Location.create(x, y);
    }

    public Location getLocation() {
        return location;
    }

    public int neighborCount() {
        return 0;
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
}
