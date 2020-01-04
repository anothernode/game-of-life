package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class GameTests {

    @Test
    public void singleCellDies() {

        Board board = new Board();
        board.add(new Cell(new Coordinates(0, 0)));
        Game game = new Game(board);

        game.iterate();

        assertThat(game.getBoard().size()).isEqualTo(0);
    }

    @Test
    public void twoCellsDie() {

        Board board = new Board();
        board.add(new Cell(new Coordinates(0, 0)));
        Game game = new Game(board);

        game.iterate();

        assertThat(game.getBoard().size()).isEqualTo(0);
    }
}
