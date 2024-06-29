package org.snakegame.model;

import java.util.Objects;

public class Coordinate {

  private int row;
  private int column;

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public Coordinate(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    Coordinate that = (Coordinate) object;
    return row == that.row && column == that.column;
  }

  public int hashCode() {
    return (row+column);
  }
}