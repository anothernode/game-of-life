package com.anothernode.gameoflife.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class SquareTests {

  @Test
  void squareCanReturnItsNeighborSquares() {
    var square1 = Square.create(3, -2);
    var neighborSquares1 = square1.getNeighborSquares();
    var square2 = Square.create(5, 8);
    var neighborSquares2 = square2.getNeighborSquares();

    assertThat(neighborSquares1).containsExactly(
        Square.create(2, -3), Square.create(3, -3), Square.create(4, -3),
        Square.create(2, -2), Square.create(4, -2),
        Square.create(2, -1), Square.create(3, -1), Square.create(4, -1));

    assertThat(neighborSquares2).containsExactly(
        Square.create(4, 7), Square.create(5, 7), Square.create(6, 7),
        Square.create(4, 8), Square.create(6, 8),
        Square.create(4, 9), Square.create(5, 9), Square.create(6, 9));
  }

  @Test
  void squaresCompareCorrectly() {
    var square1 = Square.create(0, 0);
    var square2 = Square.create(5, 3);
    var square3 = Square.create(8, 3);
    var square4 = Square.create(3, 8);
    var square5 = Square.create(3, 8);

    assertThat(square2).isStrictlyBetween(square1, square3);
    assertThat(square3).isStrictlyBetween(square2, square4);
    assertThat(square4).isEqualByComparingTo(square5);
  }
}
