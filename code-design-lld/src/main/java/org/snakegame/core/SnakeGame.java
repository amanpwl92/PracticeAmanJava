package org.snakegame.core;

import org.snakegame.enums.Direction;
import org.snakegame.exceptions.GameOverException;
import org.snakegame.model.Coordinate;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/*
 *
 * Enum direction {right, left, up, downs}
 *
 * Coordinate { -- if we can name it beter
 * int x;
 * int y;
 *
 *
 * }
 *
 * Snake Game {
 * no of rows
 * no of Columns
 *
 * List coordinate -> snake -> linkedList
 *
 * initialSize -> 3;
 *
 * growing size -> 1
 *
 * growing step count -> 5
 *
 * constructor ()
 *
 * moveSnake(direction)
 *
 *
 * }
 *
 * isGameOver() -> GameOverException
 *
 * isValidGame() -> InvalidGameException
 *
 *
 * */
public class SnakeGame {

  int rows;
  int columns;

  int initialSize;

  int growSize;

  int growStepCount;

  LinkedList<Coordinate> snake;

  HashSet<Coordinate> snakeSet;

  int moveCount;

  public LinkedList<Coordinate> getSnake() {
    return snake;
  }

  public SnakeGame(int rows, int columns, int initialSize, int growSize, int growStepCount) {
    this.rows = rows;
    this.columns = columns;
    this.initialSize = initialSize;
    this.growSize = growSize;
    this.growStepCount = growStepCount;
    this.snake = new LinkedList<>();
    snakeSet = new HashSet<>();
    moveCount = 0;
//    isValidGame();

    createInitialSnake();
  }

  public void createInitialSnake() {

    int row = 0;
    int column = 0;

    do {
      Coordinate coordinate = new Coordinate(row, column);
      snake.addLast(coordinate);
      column++;
      initialSize--;
    } while (initialSize > 0);

  }

  public void isGameOver(Coordinate coordinate) {

    if (snakeSet.contains(coordinate)) {
      throw new GameOverException("Game is over as snake hit its body");
    }

  }

//  public boolean isValidGame() {
//
//  }

  public Coordinate getCoordinate(Direction direction) {
    Coordinate head = snake.getLast();
    int row, column;
    row = head.getX();
    column = head.getY();

    if (direction == Direction.RIGHT) {
      column++;

      if (column >= columns) {
        column=0;
      }

    } else if (direction == Direction.LEFT) {
      column--;

      if (column < 0) {
        column=columns-1;
      }

    } else if (direction == Direction.UP) {
      row--;

      if (row < 0) {
        row = rows -1;
      }
    } else {

      row++;

      if (row >= rows) {
        row = 0;
      }

    }

    return new Coordinate(row, column);
  }

  public void moveSnake(Direction direction) {
    Coordinate newCoordinate = getCoordinate(direction);


    Coordinate tail = snake.getFirst();
    snake.removeFirst();
    snakeSet.remove(tail);

    isGameOver(newCoordinate);

    snake.addLast(newCoordinate);
    snakeSet.add(newCoordinate);
    moveCount++;

    if (moveCount % growStepCount == 0) {
      growSnake(direction);
    }

  }

  public void growSnake(Direction direction) {

    int growCounter = growSize;
    Coordinate newCoordinate;

    while (growCounter > 0) {

      newCoordinate = getCoordinate(direction);
      snake.addLast(newCoordinate);
      growCounter--;

    }

  }
}
