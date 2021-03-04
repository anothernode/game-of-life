package com.anothernode.gameoflife.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;

public class Game {

  private String id = RandomStringUtils.randomAlphanumeric(10);

  private List<Round> rounds = new ArrayList<>();

  public Game() {
    this.rounds.add(new Round());
  }

  public Game(String id) {
    this.id = id;
    this.rounds.add(new Round());
  }

  public Game(Round firstRound) {
    this.rounds.add(firstRound);
  }

  /**
   * Creates a <i>Game</i> with a given start configuration expressed as as set of the {@link Cell}s
   * on the {@link Round} in {@link Round} zero (before the first iteration).
   *
   * @param cells the living {@link Cell}s before the first iteration
   */
  public Game(Set<Cell> cells) {
    this.rounds.add(new Round());
    cells.forEach((cell -> this.rounds.get(0).add(cell)));
  }

  public List<Round> getRounds() {
    return rounds;
  }

  public Round getRound(int i) {
    return rounds.get(i);
  }

  public String getId() {
    return id;
  }

  public Set<Cell> getCellsInFirstRound() {
    return rounds.get(0).getCells();
  }

  public void iterate() {
    var currentRound = rounds.get(rounds.size() - 1);
    var nextRound = new Round();
    nextRound.add(currentRound.calculateSurvivingCells());
    nextRound.add(currentRound.calculateNewbornCells());
    rounds.add(nextRound);
  }

}
