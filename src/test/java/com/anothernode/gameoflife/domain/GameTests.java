package com.anothernode.gameoflife.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GameTests {

    @Test
    public void gameWithStartConfigurationIsCreatedCorrectly() {
        Set<Cell> cells = Set.of(new Cell(0, 0), new Cell(2, 2));
        Game game = new Game(cells);

        assertThat(game.cells().size()).isEqualTo(2);
        assertThat(game.cells()).contains(new Cell(0, 0), new Cell(2, 2));
    }

    @Test
    public void cellWithoutNeighborDies() {

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

    @Disabled // TODO
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
