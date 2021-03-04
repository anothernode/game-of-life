package com.anothernode.gameoflife.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GameTests {

  @Test
  void gameWithStartConfigurationIsCreatedCorrectly() {
    var cells = Set.of(new Cell(0, 0), new Cell(2, 2));
    var game = new Game(cells);

    assertThat(game.getCellsInFirstRound().size()).isEqualTo(2);
    assertThat(game.getCellsInFirstRound()).contains(new Cell(0, 0), new Cell(2, 2));
  }

  @Test
  void iteratingAddsRoundToGame() {
    var game = new Game();
    game.iterate();

    assertThat(game.getRounds().size()).isEqualTo(2);
  }

  @Test
  // TODO: move to RoundTest
  void cellsWithFewerThanTwoNeighborsDie() {
    var game = new Game(Set.of(new Cell(0, 0), new Cell(2, 0), new Cell(3, 0)));
    game.iterate();

    assertThat(game.getRound(1).cellCount()).isZero();
  }

  @Test
  // TODO: move to RoundTest
  void cellsWithTwoNeighborsLiveOn() {
    var game = new Game(Set.of(
        new Cell(0, 0), new Cell(1, 0),
        new Cell(0, 1),
        new Cell(0, 3), new Cell(1, 3), new Cell(2, 3)));
    game.iterate();
    var nextRound = game.getRound(1);

    assertThat(nextRound.getCells()).contains(
        new Cell(0, 0), new Cell(0, 1), new Cell(1, 0), new Cell(1, 3));
  }

  @Test
  // TODO: move to RoundTest
  void cellsWithThreeNeighborsLiveOn() {
    var game = new Game(Set.of(
        new Cell(0, 0), new Cell(1, 0), new Cell(3, 0),
        new Cell(0, 1), new Cell(3, 1), new Cell(4, 1),
        new Cell(0, 2), new Cell(3, 2)));
    game.iterate();
    var nextRound = game.getRound(1);

    assertThat(nextRound.hasCell(1, 0)).isTrue();
    assertThat(nextRound.hasCell(3, 1)).isTrue();
    assertThat(nextRound.hasCell(4, 1)).isTrue();
  }

  @Test
  // TODO: move to RoundTest
  void cellsWithMoreThanThreeNeighborsDie() {
    var game = new Game(Set.of(
        new Cell(0, 0), new Cell(1, 0),
        new Cell(0, 1), new Cell(1, 1),
        new Cell(0, 2)));
    game.iterate();
    var nextRound = game.getRound(1);

    assertThat(nextRound.hasCell(0, 1)).isFalse();
    assertThat(nextRound.hasCell(1, 1)).isFalse();
  }

  @Test
  // TODO: move to RoundTests
  void cellsAreBornOnEmptySquaresWithExactlyThreeNeighboringCells() {
    var game = new Game(Set.of(
        new Cell(-3, -3), new Cell(-2, -3), new Cell(-1, -3),
        new Cell(-3, -2),
        new Cell(-4, 1), new Cell(1, 1),
        new Cell(-4, 2), new Cell(0, 2), new Cell(1, 2),
        new Cell(1, 3)));
    game.iterate();
    var nextRound = game.getRound(1);

    assertThat(nextRound.getCells()).contains(
        new Cell(-2, -4), new Cell(0, 1), new Cell(2, 2), new Cell(0, 3));
  }
}
