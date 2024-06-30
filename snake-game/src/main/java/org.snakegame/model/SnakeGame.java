package org.snakegame.model;

import org.snakegame.enums.Direction;
import org.snakegame.exceptions.InvalidGameStartException;
import org.snakegame.exceptions.SnakeDeadException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SnakeGame {

  private LinkedList<Coordinate> coordinates;
  private Direction currentDirection;
  private int noOfRows;
  private int noOfColumns;
  private int initialSize;
  private int growSize;
  private int growAfterStep;

  private int stepCount=0;

  public SnakeGame(Direction currentDirection, int noOfRows, int noOfColumns, int initialSize, int growSize,
                   int growAfterStep) {
    this.currentDirection = currentDirection;
    this.noOfRows = noOfRows;
    this.noOfColumns = noOfColumns;
    this.initialSize = initialSize;
    this.growSize = growSize;
    this.growAfterStep = growAfterStep;

    coordinates = new LinkedList<>();

    if((currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT) && (initialSize > noOfColumns)) {
      throw new InvalidGameStartException("Invalid Game");
    }

    if((currentDirection == Direction.TOP || currentDirection == Direction.BOTTOM) && (initialSize > noOfRows)) {
      throw new InvalidGameStartException("Invalid Game");
    }

    if(growSize > growAfterStep) {
      throw new InvalidGameStartException("Invalid Game");
    }

    int startRow = noOfRows/2;
    int startColumn = noOfColumns/2;
    coordinates.addFirst(new Coordinate(startRow, startColumn));
    Coordinate coordinate;
    for (int i=2; i<= initialSize; i++) {
      coordinate = getMovementCoordinates(startRow, startColumn);
      coordinates.addFirst(coordinate);
      startRow = coordinate.getRow();
      startColumn = coordinate.getColumn();
    }

  }

  private Coordinate getMovementCoordinates(int startRow, int startColumn) {

    if(currentDirection == Direction.LEFT) {
      startColumn--;

      if(startColumn < 1) {
        startColumn = noOfColumns;
      }
    }

    if(currentDirection == Direction.RIGHT) {
      startColumn++;

      if(startColumn > noOfColumns) {
        startColumn = 1;
      }
    }

    if(currentDirection == Direction.TOP) {
      startRow--;

      if(startRow < 1) {
        startRow = noOfRows;
      }
    }

    if(currentDirection == Direction.BOTTOM) {
      startRow++;

      if(startRow > noOfRows) {
        startRow = 1;
      }
    }

    return new Coordinate(startRow, startColumn);

  }

  public void moveOneStep() {
    Coordinate head = coordinates.getFirst();
    int startRow = head.getRow();
    int startColumn = head.getColumn();

    Coordinate newHead = getMovementCoordinates(startRow, startColumn);

    if (coordinates.contains(newHead)) {
      throw new SnakeDeadException("Game is over as snake's head has collided with its body");
    }
    coordinates.addFirst(newHead);

    coordinates.removeLast();

    stepCount++;

    if(stepCount% growAfterStep == 0) {

      int growSizeTemp = growSize;
      while(growSizeTemp != 0) {
        head = coordinates.getFirst();
        newHead = getMovementCoordinates(head.getRow(), head.getColumn());
        if (coordinates.contains(newHead)) {
          throw new SnakeDeadException("Game is over as snake's head has collided with its body");
        }
        coordinates.addFirst(newHead);
        growSizeTemp--;
      }

    }
  }

  public List<Coordinate> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(LinkedList<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  public Direction getCurrentDirection() {
    return currentDirection;
  }

  public void setCurrentDirection(Direction currentDirection) {
    this.currentDirection = currentDirection;
  }

  public int getNoOfRows() {
    return noOfRows;
  }

  public void setNoOfRows(int noOfRows) {
    this.noOfRows = noOfRows;
  }

  public int getNoOfColumns() {
    return noOfColumns;
  }

  public void setNoOfColumns(int noOfColumns) {
    this.noOfColumns = noOfColumns;
  }

  public int getInitialSize() {
    return initialSize;
  }

  public void setInitialSize(int initialSize) {
    this.initialSize = initialSize;
  }

  public int getGrowSize() {
    return growSize;
  }

  public void setGrowSize(int growSize) {
    this.growSize = growSize;
  }

  public int getGrowAfterStep() {
    return growAfterStep;
  }

  public void setGrowAfterStep(int growAfterStep) {
    this.growAfterStep = growAfterStep;
  }
}
