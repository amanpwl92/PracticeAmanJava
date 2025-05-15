package model;

import java.io.File;
import java.util.UUID;

public class FileCollection implements Comparable {
  private UUID id;
  private long size = 0;

  //

  public FileCollection(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  @Override
  public int compareTo(Object o) {
    FileCollection fileCollection = (FileCollection) o;

    if (fileCollection.size > this.size) {
      return 1;
    } else if (fileCollection.size < this.size) {
      return -1;
    } else {
      return 0;
    }

  }
}
