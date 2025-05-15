package model;

import java.util.UUID;

public class FileSystemEntity {

  public FileSystemEntity(UUID collectionId, UUID fileId, int fileSize) {
    this.collectionId = collectionId;
    this.fileId = fileId;
    this.fileSize = fileSize;
  }

  private UUID collectionId;

  private UUID fileId;

  private int fileSize;

  public UUID getCollectionId() {
    return collectionId;
  }

  public void setCollectionId(UUID collectionId) {
    this.collectionId = collectionId;
  }

  public UUID getFileId() {
    return fileId;
  }

  public void setFileId(UUID fileId) {
    this.fileId = fileId;
  }

  public int getFileSize() {
    return fileSize;
  }

  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }
}
