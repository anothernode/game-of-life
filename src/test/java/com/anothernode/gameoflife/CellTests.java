package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CellTests {

    @Test
    public void cellExists() {

        int x = -12;
        int y = -37;

        Location coordinates = Location.create(x, y);
        Cell cell = new Cell(coordinates);

        assertThat(cell.getLocation().getX()).isEqualTo(x);
        assertThat(cell.getLocation().getY()).isEqualTo(y);
    }

    @Test
    public void countNeigborsCountsZeroForNoNeighbor() {

        Cell cell = new Cell(0, 0);
        Board board = new Board();
        board.add(cell);

        assertThat(cell.neighborCount()).isEqualTo(0);
    }

    @Disabled // TODO
    @Test
    public void countNeighborsCountsOneForOneNeighbor() {

        Cell cell = new Cell(0, 0);
        Cell neighbor = new Cell(0, 1);
        Board board = new Board();
        board.add(cell);
        board.add(neighbor);

        assertThat(cell.neighborCount()).isEqualTo(1);
    }
}
