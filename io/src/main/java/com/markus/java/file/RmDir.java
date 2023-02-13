package com.markus.java.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/9
 * @Description:
 */
public class RmDir {
  public static void rmdir(Path dir) throws IOException {
    Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
      /**
       * 在这个目录下的每个文件上运行
       * @param file
       * @param attrs
       * @return
       * @throws IOException
       */
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.delete(file);
        return FileVisitResult.CONTINUE;
      }

      /**
       * 先进入当前目录下的文件和目录(包括所有的子目录)，最后在当前目录上运行
       * @param dir
       * @param exc
       * @return
       * @throws IOException
       */
      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        Files.delete(dir);
        return FileVisitResult.CONTINUE;
      }


      /**
       * 先在当前目录上运行，然后进入这个目录下的文件和目录
       * @param dir
       * @param attrs
       * @return
       * @throws IOException
       */
      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return super.preVisitDirectory(dir, attrs);
      }

      /**
       * 当文件无法访问时调用
       * @param file
       * @param exc
       * @return
       * @throws IOException
       */
      @Override
      public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return super.visitFileFailed(file, exc);
      }
    });
  }
}
