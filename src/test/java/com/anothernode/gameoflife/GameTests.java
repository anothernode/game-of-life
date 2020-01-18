package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class GameTests {

    @Test
    public void cellWithoutNeigborDies() {

        Board board = new Board();
        board.add(new Cell(0, 0));
        Game game = new Game(board);

        game.iterate();

        assertThat(game.getBoard().size()).isEqualTo(0);
    }

    @Test
    public void cellWithJustOneNeighborDies() {

        Board board = new Board();
        board.add(new Cell(0, 0));
        board.add(new Cell(1, 0));
        Game game = new Game(board);

        game.iterate();

        assertThat(game.getBoard().size()).isEqualTo(0);
    }

    @Test
    public void cellWithTwoNeighborsSurvives() {

        Board board = new Board();
        board.add(new Cell(0, 0));
        board.add(new Cell(1, 0));
        board.add(new Cell(0, 1));
        Game game = new Game(board);

        game.iterate();

        assertThat(game.getBoard().size()).isEqualTo(3);
    }
}
