package com.anothernode.gameoflife.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class RoundTests {

  private Round round = new Round();

  @Test
  void cellsCanBeAddedToBoard() {
    round.add(new Cell(-15, -33));
    round.add(new Cell(5, 18));

    assertThat(round.cellCount()).isEqualTo(2);
  }

  @Test
  void hasCellCanDetermineCellNonExistence() {
    int x = 5;
    int y = -33;

    assertThat(round.hasCell(Location.create(x, y))).isFalse();
    assertThat(round.hasCell(x, y)).isFalse();
  }

  @Test
  void hasCellCanDetermineCellExistence() {
    int x = -135;
    int y = 15;
    round.add(new Cell(x, y));

    assertThat(round.hasCell(Location.create(x, y))).isTrue();
    assertThat(round.hasCell(x, y)).isTrue();
  }

  @Test
  void countNeighborsCountsCorrectForCellWithoutNeighbor() {
    var baseCell = new Cell(0, 0);
    round.add(baseCell);

    assertThat(round.countNeighbors(baseCell)).isEqualTo(0);
  }

  @Test
  void countNeighborsCountsCorrectForCellWithOneNeighbor() {
    var baseCell = new Cell(0, 0);
    round.add(baseCell);
    round.add(new Cell(0, 1));

    assertThat(round.countNeighbors(baseCell)).isEqualTo(1);
  }

  @Test
  void countNeighborsCountsCorrectWithThreeNeighbors() {
    var baseCell = new Cell(47, 23);
    round.add(baseCell);
    round.add(new Cell(46, 22));
    round.add(new Cell(47, 24));
    round.add(new Cell(48, 24));

    assertThat(round.countNeighbors(baseCell)).isEqualTo(3);
  }

  @Test
  void countNeighborsCountsCorrectWithEightNeighbors() {
    var baseCell = new Cell(538, 5);
    round.add(baseCell);
    round.add(new Cell(537, 4));
    round.add(new Cell(537, 5));
    round.add(new Cell(537, 6));
    round.add(new Cell(538, 4));
    round.add(new Cell(538, 6));
    round.add(new Cell(539, 4));
    round.add(new Cell(539, 5));
    round.add(new Cell(539, 6));

    assertThat(round.countNeighbors(baseCell)).isEqualTo(8);
  }
}
