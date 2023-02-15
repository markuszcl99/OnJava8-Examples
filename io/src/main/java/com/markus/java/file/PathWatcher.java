package com.markus.java.file;


import com.markus.java.file.constant.FileConstant;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/10
 * @Description:
 */
public class PathWatcher {
  static Path test = Paths.get(FileConstant.CURRENT_BASE_PATH, "test");

  static void delTxtFiles() {
    try {
      Files.walk(test)
          .filter(
              f -> f.toString().endsWith(".txt")
          ).forEach(
          f -> {
            System.out.println("deleting " + f);
            try {
              Files.delete(f);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    // 刷新目录
    Directories.refreshTestDir();
    // 创建目录下的文件
    Directories.populateTestDir();

    Files.createFile(test.resolve("Hello.txt"));
    WatchService watcher = FileSystems.getDefault().newWatchService();
    // 只能监听到当前目录下操作，子目录下的文件操作不会被监听到
    test.register(watcher, ENTRY_DELETE);
    Executors.newSingleThreadScheduledExecutor().schedule(PathWatcher::delTxtFiles, 250, TimeUnit.MILLISECONDS);

    WatchKey key = watcher.take();
    for (WatchEvent<?> evt : key.pollEvents()) {
      System.out.println("evt.context(): " + evt.context() +
          "\nevt.count(): " + evt.count() +
          "\nevt.kind(): " + evt.kind());
      System.exit(0);
    }

  }
}
/** 控制台
 * deleting io/src/main/java/com/markus/java/file/test/foo/bar/baz/bag/File3.txt
 * deleting io/src/main/java/com/markus/java/file/test/baz/bag/foo/bar/File1.txt
 * deleting io/src/main/java/com/markus/java/file/test/bar/baz/bag/foo/File2.txt
 * deleting io/src/main/java/com/markus/java/file/test/bag/foo/bar/baz/File0.txt
 * deleting io/src/main/java/com/markus/java/file/test/Hello.txt
 * evt.context(): Hello.txt
 * evt.count(): 1
 * evt.kind(): ENTRY_DELETE
 */