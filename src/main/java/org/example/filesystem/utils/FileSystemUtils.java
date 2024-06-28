package org.example.filesystem.utils;

import org.example.filesystem.core.FileSystem;
import org.example.filesystem.exceptions.InvalidFolderException;
import org.example.filesystem.model.Folder;

import java.util.UUID;

public class FileSystemUtils {

  public static Folder getExistingFolder(FileSystem fileSystem, UUID folderId) throws InvalidFolderException {
    Folder folder = fileSystem.getFolders().get(folderId);

    if (folder == null) {
      throw new RuntimeException("Folder not found");
    }

    return folder;
  }
}
