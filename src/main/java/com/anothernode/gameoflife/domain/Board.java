package com.anothernode.gameoflife.domain;

import java.util.HashSet;
import java.util.Set;

public class Board {

    private Set<Cell> cells = new HashSet<>();

    public Set<Cell> getCells() {
        return cells;
    }

    public int size() {
        return cells.size();
    }

    public void add(Cell cell) {
        cells.add(cell);
    }

    public boolean hasCell(Location location) {
        return cells.contains(new Cell(location));
    }

    public boolean hasCell(int x, int y) {
        return hasCell(Location.create(x, y));
    }

    public int countNeighbors(Cell cell) {
        int count = 0;
        int baseCellX = cell.getLocation().getX();
        int baseCellY = cell.getLocation().getY();

        for (int x = baseCellX - 1; x <= baseCellX + 1; x++) {
            for (int y = baseCellY - 1; y <= baseCellY + 1; y++) {
                if (this.hasCell(x, y)) {
                    count++;
                }
            }
        }
        return count - 1;
    }
}
