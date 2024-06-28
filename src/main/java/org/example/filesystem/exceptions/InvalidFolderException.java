package org.example.filesystem.exceptions;

public class InvalidFolderException extends RuntimeException{

  @Override
  public String toString() {
    return "Invalid Folder";
  }
}
