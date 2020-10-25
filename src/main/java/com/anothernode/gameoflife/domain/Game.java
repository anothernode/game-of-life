package com.anothernode.gameoflife.domain;

import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;

public class Game {

  private String id = RandomStringUtils.randomAlphanumeric(10);

  private Board board = new Board();

  public Game() {
  }

  public Game(Board board) {
    this.board = board;
  }

  /**
   * Creates a <i>Game</i> with a given start configuration expressed as as set of the {@link Cell}s
   * on the {@link Board} in {@link Round} zero (before the first iteration).
   *
   * @param cells the living {@link Cell}s before the first iteration
   */
  public Game(Set<Cell> cells) {
    cells.forEach((cell -> board.add(cell)));
  }

  public Board getBoard() {
    return board;
  }

  public String getId() {
    return id;
  }

  public Set<Cell> cells() {
    return board.getCells();
  }

  /**
   * TODO
   */
  public void iterate() {
    board.getCells().clear();
  }
}
