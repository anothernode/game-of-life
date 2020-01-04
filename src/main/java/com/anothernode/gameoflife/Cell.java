package com.anothernode.gameoflife;

public class Cell {

    private Coordinates coordinates;

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
