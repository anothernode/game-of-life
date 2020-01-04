package com.anothernode.gameoflife;

public class Game {

    private Board board;

    public Game(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void iterate() {

        board.getCells().clear();
    }
}
