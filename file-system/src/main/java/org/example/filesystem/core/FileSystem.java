package org.example.filesystem.core;

import org.example.filesystem.model.File;
import org.example.filesystem.model.Folder;
import org.example.filesystem.utils.FileSystemUtils;

import java.util.*;

public class FileSystem {

  private Map<UUID, Folder> folders;
  private Map<UUID, File> files;

  public Folder getRootFolder() {
    return rootFolder;
  }

  private final Folder rootFolder;

  public Map<UUID, Folder> getFolders() {
    return folders;
  }

  public void setFolders(Map<UUID, Folder> folders) {
    this.folders = folders;
  }

  public Map<UUID, File> getFiles() {
    return files;
  }

  public void setFiles(Map<UUID, File> files) {
    this.files = files;
  }

  public FileSystem() {
    folders = new HashMap<>();
    files = new HashMap<>();
    rootFolder = new Folder("Root", null);
    folders.put(rootFolder.getId(), rootFolder);
  }

  public File createFile(UUID folderId, String name, String extension, byte[] content) {
    Folder folder = FileSystemUtils.getOrValidateExistingFolder(this, folderId);
    File file = new File(name, extension, content, folderId);
    files.put(file.getId(), file);
    folder.getFiles().add(file);
    int length = content.length;

    do {
      folder.setSize(folder.getSize() + length);
      folder = folders.get(folder.getParentFolderId());
    } while (folder != null);

    return file;

  }

  public Folder createFolder(String name, UUID folderId) {
    FileSystemUtils.getOrValidateExistingFolder(this, folderId);
    Folder folder = new Folder(name, folderId);
    folders.put(folder.getId(), folder);
    return folder;
  }

  public void deleteFile(UUID fileId) {
    File file = files.get(fileId);
    files.remove(file.getId());
    Folder folder = folders.get(file.getFolderId());
    folder.getFiles().remove(file);

    do {
      folder.setSize(folder.getSize() - file.getSize());
      folder = folders.get(folder.getParentFolderId());
    } while (folder != null);
  }

  public void deleteFolder(UUID folderId) {
    Folder folder = folders.get(folderId);
    folders.remove(folder.getId());
    Folder parentFolder = folders.get(folder.getParentFolderId());
    parentFolder.getSubFolders().remove(folder);

    do {
      parentFolder.setSize(parentFolder.getSize() - folder.getSize());
      parentFolder = folders.get(parentFolder.getParentFolderId());
    } while (parentFolder != null);

  }


  public long getSize(UUID folderId) {
    Folder folder = FileSystemUtils.getOrValidateExistingFolder(this, folderId);
    return folder.getSize();
  }
}
