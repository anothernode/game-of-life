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

    }
}
