package com.anothernode.gameoflife.domain;

import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;

public class Game {

  private String id = RandomStringUtils.randomAlphanumeric(10);

  private Round round = new Round();

  public Game() {
  }

  public Game(String id) {
    this.id = id;
  }

  public Game(Round board) {
    this.round = board;
  }

  /**
   * Creates a <i>Game</i> with a given start configuration expressed as as set of the {@link Cell}s
   * on the {@link Round} in {@link Round} zero (before the first iteration).
   *
   * @param cells the living {@link Cell}s before the first iteration
   */
  public Game(Set<Cell> cells) {
    cells.forEach((cell -> round.add(cell)));
  }

  public Round getRound() {
    return round;
  }

  public String getId() {
    return id;
  }

  public Set<Cell> cells() {
    return round.getCells();
  }

  /**
   * TODO
   */
  public void iterate() {
    round.getCells().clear();
  }
}
