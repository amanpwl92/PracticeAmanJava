package org.snakegame.model;

import org.junit.Before;
import org.junit.Test;
import org.snakegame.enums.Direction;
import org.snakegame.exceptions.InvalidGameStartException;
import org.snakegame.exceptions.SnakeDeadException;

import static junit.framework.TestCase.assertEquals;

public class SnakeGameTest {

  private static SnakeGame snakeGame;

  @Before
  public void initialize() {
    snakeGame = new SnakeGame(Direction.RIGHT, 6, 10, 3, 2, 4);
  }

  @Test
  public void basicTest() {
    assertEquals(snakeGame.getCoordinates().size(), 3);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(3, 5)), true);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(3, 6)), true);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(3, 7)), true);

    snakeGame.setCurrentDirection(Direction.TOP);
    snakeGame.moveOneStep();
    snakeGame.moveOneStep();
    snakeGame.moveOneStep();
    assertEquals(snakeGame.getCoordinates().size(), 3);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(6, 7)), true);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(2, 7)), true);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(1, 7)), true);

  }

  @Test(expected = InvalidGameStartException.class)
  public void invalidGame() {
    snakeGame = new SnakeGame(Direction.TOP, 6, 10, 7, 2, 4);
  }

  @Test
  public void testSnakeDead() {
    snakeGame = new SnakeGame(Direction.RIGHT, 6, 10, 3, 2, 4);

    snakeGame.moveOneStep();
    snakeGame.moveOneStep();
    snakeGame.moveOneStep();
    snakeGame.moveOneStep();
    assertEquals(snakeGame.getCoordinates().size(), 5);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(3, 1)), true);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(3, 2)), true);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(3, 3)), true);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(3, 9)), true);
    assertEquals(snakeGame.getCoordinates().contains(new Coordinate(3, 10)), true);

    snakeGame.setCurrentDirection(Direction.TOP);
    snakeGame.moveOneStep();
    snakeGame.moveOneStep();

    snakeGame.setCurrentDirection(Direction.LEFT);
    snakeGame.moveOneStep();




  }






}
