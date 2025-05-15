package org.snakegame.model;

public class Coordinate {
  int x;
  int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public int hashCode() {
    return x + y;
  }

  @Override
  public boolean equals(Object obj) {
    Coordinate coordinate = (Coordinate) obj;
    return x == coordinate.x && y == coordinate.y;
  }
}


//2, 4 -> 2 -> 5
//
//3, 4 -> 4, 3



