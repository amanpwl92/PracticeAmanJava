package org.example.filesystem.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class File {
  private UUID id;
  private String name;
  private String extension;
  private long size;
  private Date createdDate;
  private Date lastModifiedDate;
  private Date lastAccessedDate;
  private byte[] content;
  private UUID folderId;

  public File(String name, String extension, byte[] content, UUID folderId) {
    this.name = name;
    this.extension = extension;
    this.content = content;
    this.folderId = folderId;
    this.id = UUID.randomUUID();
    this.size = content.length;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public Date getLastAccessedDate() {
    return lastAccessedDate;
  }

  public void setLastAccessedDate(Date lastAccessedDate) {
    this.lastAccessedDate = lastAccessedDate;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public UUID getFolderId() {
    return folderId;
  }

  public void setFolderId(UUID folderId) {
    this.folderId = folderId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    File file = (File) o;
    return id.equals(file.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
