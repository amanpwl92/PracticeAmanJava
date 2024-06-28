package org.example.filesystem.core;

import org.example.filesystem.exceptions.InvalidFolderException;
import org.example.filesystem.model.File;
import org.example.filesystem.model.Folder;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

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

    fileSystem.createFile(folder1.getId(), "file1", "txt", new byte[3000]);
    fileSystem.createFile(folder2.getId(), "file2", "txt", new byte[5000]);
    fileSystem.createFile(folder3.getId(), "file3", "txt", new byte[8000]);

    long size = fileSystem.getSize(folder1.getId());
    assertEquals(size, 3000);

    size = fileSystem.getSize(folder2.getId());
    assertEquals(size, 5000);

    size = fileSystem.getSize(folder3.getId());
    assertEquals(size, 8000);

    size = fileSystem.getSize(root.getId());
    assertEquals(size, 16000);
  }

  /**
   * test file addition on invalid folder
   */
  @Test(expected = InvalidFolderException.class)
  public void testAddFileOnNonExistingFolder() {
    Folder root = fileSystem.getRootFolder();
    fileSystem.createFolder("F1", root.getId());
    fileSystem.createFile(UUID.randomUUID(), "file1", "txt", new byte[10000]);
  }

  /**
   * 3 directories at root level each having 1,2,3 files each of size 1000 ... 6000
   * assert size of each directory inc root
   * delete folder1 and then assert size of remaining directories
   * delete single file from folder 2 and assert size of all directories
   */
  @Test
  public void testComplexCaseWithCreationAndDeletion() {
    Folder root = fileSystem.getRootFolder();

    // 3 directories at root level each having 1,2,3 files each of size 1000 ... 6000
    Folder folder1 = fileSystem.createFolder("F1", root.getId());
    Folder folder2 = fileSystem.createFolder("F2", root.getId());
    Folder folder3 = fileSystem.createFolder("F3", root.getId());

    fileSystem.createFile(folder1.getId(), "file1", "txt", new byte[1000]);

    File file2 = fileSystem.createFile(folder2.getId(), "file2", "txt", new byte[2000]);
    fileSystem.createFile(folder2.getId(), "file3", "txt", new byte[3000]);

    fileSystem.createFile(folder3.getId(), "file4", "txt", new byte[4000]);
    fileSystem.createFile(folder3.getId(), "file5", "txt", new byte[5000]);
    fileSystem.createFile(folder3.getId(), "file6", "txt", new byte[6000]);

    //asserting size of each directory including root
    long size = fileSystem.getSize(folder1.getId());
    assertEquals(size, 1000);

    size = fileSystem.getSize(folder2.getId());
    assertEquals(size, 5000);

    size = fileSystem.getSize(folder3.getId());
    assertEquals(size, 15000);

    size = fileSystem.getSize(root.getId());
    assertEquals(size, 21000);

    //delete folder1 and then assert size of remaining directories
    fileSystem.deleteFolder(folder1.getId());

    size = fileSystem.getSize(folder2.getId());
    assertEquals(size, 5000);

    size = fileSystem.getSize(folder3.getId());
    assertEquals(size, 15000);

    size = fileSystem.getSize(root.getId());
    assertEquals(size, 20000);

    // delete single file from folder 2 and assert size of all directories
    fileSystem.deleteFile(file2.getId());

    size = fileSystem.getSize(folder2.getId());
    assertEquals(size, 3000);

    size = fileSystem.getSize(folder3.getId());
    assertEquals(size, 15000);

    size = fileSystem.getSize(root.getId());
    assertEquals(size, 18000);

  }

}
