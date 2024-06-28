package org.example.filesystem.core;

import org.example.filesystem.model.Folder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileSystemTest {

  private FileSystem fileSystem;

  @Before
  public void initialize() {
    fileSystem = new FileSystem();
  }

  /**
   * Test having 3 directories at root level each having single file of 3000, 5000, 8000 bytes respectively
   */
  @Test
  public void testBasic() {
    Folder root = fileSystem.getRootFolder();

    Folder folder1 = fileSystem.createFolder("F1", root.getId());
    Folder folder2 = fileSystem.createFolder("F2", root.getId());
    Folder folder3 = fileSystem.createFolder("F3", root.getId());

    byte[] size1 = new byte[3000];
    byte[] size2 = new byte[5000];
    byte[] size3 = new byte[8000];

    fileSystem.createFile(folder1.getId(), "file1", "txt", size1);
    fileSystem.createFile(folder2.getId(), "file2", "txt", size2);
    fileSystem.createFile(folder3.getId(), "file3", "txt", size3);

    long size = fileSystem.getSize(folder1.getId());
    assertEquals(size, 3000);

    size = fileSystem.getSize(folder2.getId());
    assertEquals(size, 5000);

    size = fileSystem.getSize(folder3.getId());
    assertEquals(size, 8000);

    size = fileSystem.getSize(root.getId());
    assertEquals(size, 16000);
  }
}
