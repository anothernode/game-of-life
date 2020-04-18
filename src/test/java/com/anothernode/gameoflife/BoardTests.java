package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BoardTests {

    private Board board = new Board();

    @Test
    public void cellsCanBeAddedToBoard() {

        board.add(new Cell(-15, -33));
        board.add(new Cell(5, 18));

        assertThat(board.size()).isEqualTo(2);
    }

    @Test
    public void hasCellCanDetermineCellNonExistence() {

        int x = 5;
        int y = -33;

        assertThat(board.hasCell(Location.create(x, y))).isFalse();
        assertThat(board.hasCell(x, y)).isFalse();
    }

    @Test
    public void hasCellCanDetermineCellExistence() {

        int x = -135;
        int y = 15;

        board.add(new Cell(x, y));

        assertThat(board.hasCell(Location.create(x, y))).isTrue();
        assertThat(board.hasCell(x, y)).isTrue();
    }

    @Test
    public void countNeighborsCountsCorrectForCellWithoutNeighbor() {

        Cell baseCell = new Cell(0, 0);
        board.add(baseCell);

        assertThat(board.countNeighbors(baseCell)).isEqualTo(0);
    }

    @Test
    public void countNeighborsCountsCorrectForCellWithOneNeighbor() {

        Cell baseCell = new Cell(0, 0);
        board.add(baseCell);
        board.add(new Cell(0, 1));

        assertThat(board.countNeighbors(baseCell)).isEqualTo(1);
    }

    @Test
    public void countNeighborsCountsCorrectWithThreeNeighbors() {

        Cell baseCell = new Cell(47, 23);
        board.add(baseCell);
        board.add(new Cell(46, 22));
        board.add(new Cell(47, 24));
        board.add(new Cell(48, 24));

        assertThat(board.countNeighbors(baseCell)).isEqualTo(3);
    }

    @Test
    public void countNeighborsCountsCorrectWithEightNeighbors() {

        Cell baseCell = new Cell(538, 5);
        board.add(baseCell);
        board.add(new Cell(537, 4));
        board.add(new Cell(537, 5));
        board.add(new Cell(537, 6));
        board.add(new Cell(538, 4));
        board.add(new Cell(538, 6));
        board.add(new Cell(539, 4));
        board.add(new Cell(539, 5));
        board.add(new Cell(539, 6));

        assertThat(board.countNeighbors(baseCell)).isEqualTo(8);
    }
}
