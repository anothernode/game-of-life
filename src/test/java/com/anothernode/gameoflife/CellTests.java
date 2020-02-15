package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CellTests {

    @Test
    public void cellHasCorrectCoordinates() {

        int x = -12;
        int y = -37;

        Location coordinates = Location.create(x, y);
        Cell cell = new Cell(coordinates);

        assertThat(cell.getLocation().getX()).isEqualTo(x);
        assertThat(cell.getLocation().getY()).isEqualTo(y);
    }

    @Test
    public void cellsWithEqualLocationsAreEqual() {

        Cell cell1 = new Cell(3, 5);
        Cell cell2 = new Cell(3, 5);

        assertThat(cell1).isEqualTo(cell2);
    }

    @Test
    public void setContainsCellWithEqualLocation() {

        Cell cell1 = new Cell(7, 3);
        Cell cell2 = new Cell(7, 3);

        Set<Cell> cells = new HashSet<>();
        cells.add(cell1);

        assertThat(cells).contains(cell2);
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
