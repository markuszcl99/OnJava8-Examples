package com.markus.java.io.file;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/9
 * @Description:
 */
public class FileSystemDemo {
  static void show(String id, Object o) {
    System.out.println(id + ": " + o);
  }

  public static void main(String[] args) {
    System.out.println(System.getProperty("os.name"));
    FileSystem fs = FileSystems.getDefault();
    for (FileStore fileStore : fs.getFileStores()) {
      show("File Store", fileStore);
    }
    for (Path rd : fs.getRootDirectories()) {
      show("Root Directory", rd);
    }
    show("Separator", fs.getSeparator());
    show("UserPrincipalLookupService", fs.getUserPrincipalLookupService());
    show("isOpen", fs.isOpen());
    show("isReadOnly", fs.isReadOnly());
    show("FileSystemProvider", fs.provider());
    show("File Attribute Views", fs.supportedFileAttributeViews());
  }
}
