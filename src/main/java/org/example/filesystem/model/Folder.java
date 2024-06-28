package org.example.filesystem.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Folder {
  private UUID id;
  private String name;
  private long size;
  private Date createdDate;
  private Date lastModifiedDate;
  private UUID parentFolderId;
  private List<Folder> subFolders;
  private List<File> files;

  public Folder(String name, UUID parentFolderId) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.parentFolderId = parentFolderId;
    this.subFolders = new ArrayList<>();
    this.files = new ArrayList<>();
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

  public UUID getParentFolderId() {
    return parentFolderId;
  }

  public void setParentFolderId(UUID parentFolderId) {
    this.parentFolderId = parentFolderId;
  }

  public List<Folder> getSubFolders() {
    return subFolders;
  }

  public void setSubFolders(List<Folder> subFolders) {
    this.subFolders = subFolders;
  }

  public List<File> getFiles() {
    return files;
  }

  public void setFiles(List<File> files) {
    this.files = files;
  }
}
