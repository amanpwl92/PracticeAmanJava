import core.FileSystem;
import model.FileCollection;
import model.FileSystemEntity;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

public class FileSystemTest {

  @Test
  public void testGetSize() {

    UUID collection1 = UUID.randomUUID();
    UUID collection2 = UUID.randomUUID();
    UUID file1 = UUID.randomUUID();
    UUID file2 = UUID.randomUUID();
    UUID file3 = UUID.randomUUID();

    FileSystemEntity fileSystemEntity = new FileSystemEntity(collection1, file1, 5);
    FileSystem fileSystem = new FileSystem();
    fileSystem.addEntity(fileSystemEntity);
    fileSystemEntity = new FileSystemEntity(collection1, file2, 20);
    fileSystem.addEntity(fileSystemEntity);
    fileSystemEntity = new FileSystemEntity(collection2, file3, 30);
    fileSystem.addEntity(fileSystemEntity);

    assertEquals(fileSystem.getTotalSize(), 55);

    List<FileCollection> sortedList = fileSystem.getTopNDirectories(1);

    assertEquals(sortedList.size(), 1);
    assertEquals(sortedList.get(0).getId(), collection2);

  }
}
