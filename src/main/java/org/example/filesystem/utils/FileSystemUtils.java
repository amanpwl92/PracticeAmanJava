package org.example.filesystem.utils;

import org.example.filesystem.core.FileSystem;
import org.example.filesystem.exceptions.InvalidFolderException;
import org.example.filesystem.model.Folder;

import java.util.UUID;

public class FileSystemUtils {

  public static Folder getOrValidateExistingFolder(FileSystem fileSystem, UUID folderId) throws InvalidFolderException {
    Folder folder = fileSystem.getFolders().get(folderId);

    if (folder == null) {
      throw new InvalidFolderException();
    }

    return folder;
  }
}
