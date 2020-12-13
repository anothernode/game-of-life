package com.anothernode.gameoflife.domain;

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

  public boolean hasCell(Location location) {
    return cells.contains(new Cell(location));
  }

  public boolean hasCell(int x, int y) {
    return hasCell(Location.create(x, y));
  }

  public int countNeighbors(Cell cell) {
    var count = 0;
    var baseCellX = cell.getLocation().getX();
    var baseCellY = cell.getLocation().getY();

    for (int x = baseCellX - 1; x <= baseCellX + 1; x++) {
      for (int y = baseCellY - 1; y <= baseCellY + 1; y++) {
        if (this.hasCell(x, y)) {
          count++;
        }
      }
    }
    return count - 1;
  }

  public SortedSet<Cell> calculateSurvivingCells() {
    var survivingCells = new TreeSet<Cell>();
    for (var cell : cells) {
      var count = countNeighbors(cell);
      if (count == 2 || count == 3) {
        survivingCells.add(cell);
      }
    }
    return survivingCells;
  }

  public SortedSet<Cell> calculateNewbornCells() {
    // this method should actually on the round

    // create a list of all empty squares neighboring cells
    // for each of those squares, count the neighboring living cells
    // if that count is equal to three:
    //   add a cell on that square in the next round

    return new TreeSet<Cell>();
  }
}
