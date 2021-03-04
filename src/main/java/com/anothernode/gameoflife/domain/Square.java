package com.anothernode.gameoflife.domain;

import static java.util.Comparator.comparingInt;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

  @JsonIgnore
  public SortedSet<Square> getNeighborSquares() {
    var result = new TreeSet<Square>();
    var x = this.getX();
    var y = this.getY();
    result.addAll(Set.of(
      Square.create(x - 1, y - 1), Square.create(x, y - 1), Square.create(x + 1, y - 1),
      Square.create(x - 1, y), Square.create(x + 1, y),
      Square.create(x - 1, y + 1), Square.create(x, y + 1), Square.create(x + 1, y + 1)));
    return result;
  }

  @Override
  public int compareTo(Square that) {
    return COMPARATOR.compare(this, that);
  }
}
