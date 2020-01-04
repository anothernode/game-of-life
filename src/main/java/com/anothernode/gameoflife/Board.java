package com.anothernode.gameoflife;

import java.util.HashSet;
import java.util.Set;

public class Board {

    private Set<Cell> cells = new HashSet<>();

    public void add(Cell cell) {
        cells.add(cell);
    }

    public Set<Cell> getCells() {
        return cells;
    }

    public int size() {
        return cells.size();
    }
}
