package com.anothernode.gameoflife;

import java.util.Set;

public class Game {

    private Board board;

    public Game() {}

    public Game(Board board) {
        this.board = board;
    }

    /**
     * Creates a <i>Game</i> with a given start configuration expressed as as set of the
     * {@link Cell}s on the {@link Board} in {@link Round} zero (before the first iteration).
     *
     * @param cells the living {@link Cell}s before the first iteration
     */
    public Game(Set<Cell> cells) {
        this.board = new Board();
        cells.forEach((cell -> board.add(cell)));
    }

    public Board getBoard() {
        return board;
    }

    public Set<Cell> cells() {
        return board.getCells();
    }

    /**
     * Just a stub
     */
    public void iterate() {
        board.getCells().clear();
    }
}
