package com.anothernode.gameoflife.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class SquareTests {

  @Test
  void squareCanReturnItsNeighborSquares() {
    var square = Square.create(3, -2);
    var neighborSquares = square.getNeighborSquares();

    assertThat(neighborSquares).containsExactly(
      Square.create(2, -3), Square.create(3, -3), Square.create(4, -3),
      Square.create(2, -2), Square.create(4, -2),
      Square.create(2, -1), Square.create(3, -1), Square.create(4, -1));
  }
}
