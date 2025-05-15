import org.junit.Before;
import org.junit.Test;
import org.snakegame.core.SnakeGame;
import org.snakegame.enums.Direction;
import org.snakegame.exceptions.GameOverException;
import org.snakegame.model.Coordinate;

import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

public class SnakeGameTest {
  int rows;
  int columns;
  int growSize;
  int growStepCount;
  int initialSize;

  @Before
  public void initialise() {
    rows = 3;
    columns=3;
    growSize = 1;
    growStepCount = 5;
    initialSize = 3;
  }


  @Test
  public void testInitialSize() {
    SnakeGame game = new SnakeGame(rows, columns, initialSize,  growSize, growStepCount);

    LinkedList<Coordinate> snake = game.getSnake();

    assertEquals(snake.get(0).getX(), 0);
    assertEquals(snake.get(0).getY(), 0);
    assertEquals(snake.get(1).getX(), 0);
    assertEquals(snake.get(1).getY(), 1);
    assertEquals(snake.get(2).getX(), 0);
    assertEquals(snake.get(2).getY(), 2);
  }


  @Test
  public void testMovements() {
    SnakeGame game = new SnakeGame(rows, columns, initialSize,  growSize, growStepCount);

    game.moveSnake(Direction.DOWN);
    game.moveSnake(Direction.DOWN);
    game.moveSnake(Direction.LEFT);
    game.moveSnake(Direction.LEFT);
    game.moveSnake(Direction.UP);

    LinkedList<Coordinate> snake = game.getSnake();
    assertEquals(snake.size(), 4);

  }

  @Test(expected = GameOverException.class)
  public void testGameOver() {
    SnakeGame game = new SnakeGame(rows, columns, initialSize,  growSize, growStepCount);

    game.moveSnake(Direction.DOWN);
    game.moveSnake(Direction.DOWN);
    game.moveSnake(Direction.LEFT);
    game.moveSnake(Direction.LEFT);
    game.moveSnake(Direction.UP);

    LinkedList<Coordinate> snake = game.getSnake();
    assertEquals(snake.size(), 4);

    game.moveSnake(Direction.RIGHT);
    game.moveSnake(Direction.RIGHT);
    game.moveSnake(Direction.DOWN);
    game.moveSnake(Direction.DOWN);
    game.moveSnake(Direction.LEFT);

    snake = game.getSnake();
    assertEquals(snake.size(), 5);

    game.moveSnake(Direction.UP);
    game.moveSnake(Direction.RIGHT);
    game.moveSnake(Direction.DOWN); // snake gets killed here

  }


}
