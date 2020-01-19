package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BoardTests {

    @Test
    public void cellsCanBeAddedToBoard() {

        Cell cell1 = new Cell(-15, -33);
        Cell cell2 = new Cell(5, 18);
        Board board = new Board();

        board.add(cell1);
        board.add(cell2);

        assertThat(board.size()).isEqualTo(2);
    }

    @Test
    public void countNeighborsCountsZeroForCellWithoutNeighbor() {

        Cell cell = new Cell(0, 0);
        Board board = new Board();
        board.add(cell);

        assertThat(board.countNeighbors(cell)).isEqualTo(0);
    }

    @Test
    public void countNeighborsCountsOneForCellWithOneNeighbor() {

        Cell cell1 = new Cell(0, 0);
        Cell cell2 = new Cell(0, 1);
        Board board = new Board();
        board.add(cell1);
        board.add(cell2);

        assertThat(board.countNeighbors(cell1)).isEqualTo(1);
    }
}
