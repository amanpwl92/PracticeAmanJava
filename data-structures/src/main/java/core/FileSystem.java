package core;

//import java.nio.file.Files;
import model.FileCollection;
import model.FileSystemEntity;

import java.util.*;

public class FileSystem {

  Map<UUID, FileCollection>  fileCollectionMap;
  List<FileCollection> fileCollections;
  private int totalSize = 0;

  public FileSystem() {
    this.fileCollectionMap = new HashMap<>();
    this.fileCollections = new ArrayList<>();
  }

  public void addEntity(FileSystemEntity fileSystemEntity) {

    FileCollection collection = fileCollectionMap.get(fileSystemEntity.getCollectionId());

    if (collection == null) {
      collection = new FileCollection(fileSystemEntity.getCollectionId());
      fileCollectionMap.put(fileSystemEntity.getCollectionId(), collection);
      fileCollections.add(collection);
    }

    collection.setSize(collection.getSize() + fileSystemEntity.getFileSize());
    totalSize = totalSize + fileSystemEntity.getFileSize();
  }

  public long getTotalSize() {
    return totalSize;
  }

  public List<FileCollection> getTopNDirectories(int n) {
    Collections.sort(fileCollections);

    List<FileCollection> topNDirectories = new ArrayList<>();

    for(int i =0; i< n; i++) {
      topNDirectories.add(fileCollections.get(0));
    }

    return topNDirectories;
  }



  //

}
