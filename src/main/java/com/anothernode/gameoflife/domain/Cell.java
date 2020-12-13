package com.anothernode.gameoflife.domain;

import static java.text.MessageFormat.format;

public class Cell implements Comparable<Cell> {

    private Square square;

    public Cell() {}

    public Cell(Square square) {
        this.square = square;
    }

    public Cell(int x, int y) {
        this.square = Square.create(x, y);
    }

    public Square getSquare() {
        return square;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Cell)) {
            return false;
        } else {
            return this.square.equals(((Cell) that).getSquare());
        }
    }

    @Override
    public int hashCode() {
        return this.getSquare().hashCode();
    }

    @Override
    public String toString() {
        return format("Cell[x = {0}, y = {1}]",
                getSquare().getX(),
                getSquare().getY());
    }

    @Override
    public int compareTo(Cell that) {
        if (this.equals(that)) return 0;
        if (this.square.getY() < that.square.getY()) return -1;
        if (this.square.getY() > that.square.getY()) return 1;
        if (this.square.getX() < that.square.getX()) return -1;
        if (this.square.getX() > that.square.getX()) return 1;
        return 0;
    }
}
