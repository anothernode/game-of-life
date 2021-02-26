package com.anothernode.gameoflife.domain;

import static java.util.Comparator.comparingInt;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Square implements Comparable<Square> {

  public abstract int getX();
  public abstract int getY();

  private static final Comparator<Square> COMPARATOR =
      comparingInt(Square::getY).thenComparingInt(Square::getX);

  @JsonCreator
  public static Square create(@JsonProperty("x") int x, @JsonProperty("y") int y) {
    return new AutoValue_Square(x, y);
  }

  public Set<Square> getNeighborSquares() {
    var neighborSquares = new HashSet<Square>();

    return neighborSquares;
  }

  @Override
  public int compareTo(Square that) {
    return COMPARATOR.compare(this, that);
  }
}
