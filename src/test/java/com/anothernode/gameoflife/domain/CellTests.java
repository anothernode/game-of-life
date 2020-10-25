package com.anothernode.gameoflife.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CellTests {

    @Test
    void cellHasCorrectCoordinates() {

        int x = -12;
        int y = -37;

        Location location = Location.create(x, y);
        Cell cell = new Cell(location);

        assertThat(cell.getLocation().getX()).isEqualTo(x);
        assertThat(cell.getLocation().getY()).isEqualTo(y);
    }

    @Test
    void cellsWithEqualLocationsAreEqual() {

        Cell cell1 = new Cell(3, 5);
        Cell cell2 = new Cell(3, 5);

        assertThat(cell1).isEqualTo(cell2);
    }

    @Test
    void setContainsCellWithEqualLocation() {

        Cell cell1 = new Cell(7, 3);
        Cell cell2 = new Cell(7, 3);

        Set<Cell> cells = new HashSet<>();
        cells.add(cell1);

        assertThat(cells).contains(cell2);
    }

    @Test
    void cellHasCorrectStringRepresentation() {

        Cell cell = new Cell(8,3);

        assertThat(cell.toString()).isEqualTo("Cell[x = 8, y = 3]");
    }

    @Test
    void countNeighborsCountsZeroForNoNeighbor() {

        Cell cell = new Cell(0, 0);
        Board board = new Board();
        board.add(cell);

        assertThat(cell.neighborCount()).isEqualTo(0);
    }

    @Disabled // TODO
    @Test
    void countNeighborsCountsOneForOneNeighbor() {

        Cell cell = new Cell(0, 0);
        Cell neighbor = new Cell(0, 1);
        Board board = new Board();
        board.add(cell);
        board.add(neighbor);

        assertThat(cell.neighborCount()).isEqualTo(1);
    }
}
