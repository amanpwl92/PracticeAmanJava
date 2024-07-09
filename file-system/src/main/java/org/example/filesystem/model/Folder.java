package org.example.filesystem.model;

import java.util.*;

public class Folder {
  private UUID id;
  private String name;
  private long size;
  private Date createdDate;
  private Date lastModifiedDate;
  private UUID parentFolderId;
  private Set<Folder> subFolders;
  private Set<File> files;

  public Folder(String name, UUID parentFolderId) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.parentFolderId = parentFolderId;
    this.subFolders = new HashSet<>();
    this.files = new HashSet<>();
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

  public Set<Folder> getSubFolders() {
    return subFolders;
  }

  public void setSubFolders(Set<Folder> subFolders) {
    this.subFolders = subFolders;
  }

  public Set<File> getFiles() {
    return files;
  }

  public void setFiles(Set<File> files) {
    this.files = files;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Folder folder = (Folder) o;
    return id.equals(folder.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
