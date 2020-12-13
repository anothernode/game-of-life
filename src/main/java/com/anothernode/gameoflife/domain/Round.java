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
        if (this.hasCell(x, y)) {
          count++;
        }
      }
    }
    return count - 1;
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
    // create a list of all empty squares neighboring cells
    // for each of those squares, count the neighboring living cells
    // if that count is equal to three:
    //   add a cell on that square in the next round

    var newbornCells = new HashSet<Cell>();

    return newbornCells;
  }
}
