package org.snakegame.exceptions;

public class SnakeDeadException extends RuntimeException {
  private String text;

  public SnakeDeadException(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
