package com.anothernode.gameoflife;

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

    public int countNeighbors(Cell cell) {
        int count = 0;
        int x = cell.getLocation().getX();
        int y = cell.getLocation().getY();

        if (cells.contains(new Cell(x - 1, y + 1))) {
            count++;
        }
        Cell goodCell = new Cell(x, y + 1);
        if (cells.contains(goodCell)) {
            count++;
        }
        if (cells.contains(new Cell(x + 1, y + 1))) {
            count++;
        }
        if (cells.contains(new Cell(x + 1, y))) {
            count++;
        }
        if (cells.contains(new Cell(x + 1, y - 1))) {
            count++;
        }
        if (cells.contains(new Cell(x, y - 1))) {
            count++;
        }
        if (cells.contains(new Cell(x - 1, y - 1))) {
            count++;
        }
        if (cells.contains(new Cell(x - 1, y))) {
            count++;
        }

        return count;
    }
}
