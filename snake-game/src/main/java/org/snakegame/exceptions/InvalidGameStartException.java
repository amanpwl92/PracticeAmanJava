package org.snakegame.exceptions;

public class InvalidGameStartException extends RuntimeException{

  private String text;

  public InvalidGameStartException(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
