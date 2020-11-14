package com.anothernode.gameoflife.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashSet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CellTests {

    @Test
    void cellHasCorrectCoordinates() {
        var x = -12;
        var y = -37;

        var location = Location.create(x, y);
        var cell = new Cell(location);

        assertThat(cell.getLocation().getX()).isEqualTo(x);
        assertThat(cell.getLocation().getY()).isEqualTo(y);
    }

    @Test
    void cellsWithEqualLocationsAreEqual() {
        var cell1 = new Cell(3, 5);
        var cell2 = new Cell(3, 5);

        assertThat(cell1).isEqualTo(cell2);
    }

    @Test
    void setContainsCellWithEqualLocation() {
        var cell1 = new Cell(7, 3);
        var cell2 = new Cell(7, 3);

        var cells = new HashSet<Cell>();
        cells.add(cell1);

        assertThat(cells).contains(cell2);
    }

    @Test
    void cellsCanBeCompared() {
        var cell1 = new Cell(0, 0);
        var cell2 = new Cell(5, 3);
        var cell3 = new Cell(8, 3);
        var cell4 = new Cell(3, 8);
        var cell5 = new Cell(3, 8);

        assertThat(cell2).isStrictlyBetween(cell1, cell3);
        assertThat(cell3).isStrictlyBetween(cell2, cell4);
        assertThat(cell4).isEqualByComparingTo(cell5);
    }

    @Test
    void cellHasCorrectStringRepresentation() {
        var cell = new Cell(8,3);

        assertThat(cell).hasToString("Cell[x = 8, y = 3]");
    }

    @Test
    void countNeighborsCountsZeroForNoNeighbor() {
        var cell = new Cell(0, 0);
        var board = new Board();
        board.add(cell);

        assertThat(cell.neighborCount()).isZero();
    }

    @Disabled("Todo")
    @Test
    void countNeighborsCountsOneForOneNeighbor() {
        var cell = new Cell(0, 0);
        var neighbor = new Cell(0, 1);
        var board = new Board();
        board.add(cell);
        board.add(neighbor);

        assertThat(cell.neighborCount()).isEqualTo(1);
    }
}
