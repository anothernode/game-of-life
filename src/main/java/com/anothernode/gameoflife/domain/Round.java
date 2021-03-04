package com.anothernode.gameoflife.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Round {

  private SortedSet<Cell> cells = new TreeSet<>();

  public Set<Cell> getCells() {
    return cells;
  }

  public int cellCount() {
    return cells.size();
  }

  public void add(Cell cell) {
    cells.add(cell);
  }

  public void add(Set<Cell> cells) {
    this.cells.addAll(cells);
  }

  public boolean hasCell(Square location) {
    return cells.contains(new Cell(location));
  }

  public boolean hasCell(int x, int y) {
    return hasCell(Square.create(x, y));
  }

  public int countNeighbors(Cell cell) {
    var count = 0;
    var baseCellX = cell.getSquare().getX();
    var baseCellY = cell.getSquare().getY();

    for (int x = baseCellX - 1; x <= baseCellX + 1; x++) {
      for (int y = baseCellY - 1; y <= baseCellY + 1; y++) {
        // The base cell should never get counted
        if (this.hasCell(x, y) && !cell.equals(new Cell(x, y))) {
          count++;
        }
      }
    }
    return count;
  }

  public Set<Cell> calculateSurvivingCells() {
    var survivingCells = new HashSet<Cell>();
    for (var cell : cells) {
      var count = countNeighbors(cell);
      if (count == 2 || count == 3) {
        survivingCells.add(cell);
      }
    }
    return survivingCells;
  }

  public Set<Cell> calculateNewbornCells() {
    var squaresNeighboringCells = new HashSet<Square>();
    for (Cell cell : this.cells) {
      squaresNeighboringCells.addAll(cell.getSquare().getNeighborSquares());
    }
    var newbornCells = new HashSet<Cell>();
    for (Square square : squaresNeighboringCells) {
      if (!hasCell(square)) {
        var cell = new Cell(square);
        if (countNeighbors(cell) == 3) {
          newbornCells.add(cell);
        }
      }
    }
    return newbornCells;
  }
}
