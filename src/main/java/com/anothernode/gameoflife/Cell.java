package com.anothernode.gameoflife;

public class Cell {

    private Coordinates coordinates;

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Cell(int x, int y) {
        this.coordinates = new Coordinates(x, y);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int countNeighbors() {
        return 0;
    }
}
