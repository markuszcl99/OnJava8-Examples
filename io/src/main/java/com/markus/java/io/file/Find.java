package com.markus.java.io.file;


import com.markus.java.io.file.constant.FileConstant;

import java.io.IOException;
import java.nio.file.*;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/10
 * @Description:
 */
public class Find {
  public static void main(String[] args) throws IOException {
    Path test = Paths.get(FileConstant.CURRENT_BASE_PATH, "test");
    Directories.refreshTestDir();
    Directories.populateTestDir();
    Files.createDirectory(test.resolve("dir.tmp"));

    PathMatcher matcher = FileSystems.getDefault()
        .getPathMatcher("glob:**/*.{tmp,txt}");
    Files.walk(test)
        .filter(matcher::matches)
        .forEach(System.out::println);
    System.out.println("*******************");

    PathMatcher matcher2 = FileSystems.getDefault()
        .getPathMatcher("glob:*.tmp");
    Files.walk(test)
        .map(Path::getFileName)
        .filter(matcher2::matches)
        .forEach(System.out::println);
    System.out.println("*******************");

    Files.walk(test)
        .filter(Files::isRegularFile)
        .map(Path::getFileName)
        .filter(matcher2::matches)
        .forEach(System.out::println);
  }
}
/** 控制台
 * io/src/main/java/com/markus/java/file/test/foo/bar/baz/bag/File3.txt
 * io/src/main/java/com/markus/java/file/test/foo/bar/baz/bag/2767500121528410890.tmp
 * io/src/main/java/com/markus/java/file/test/baz/bag/foo/bar/File1.txt
 * io/src/main/java/com/markus/java/file/test/baz/bag/foo/bar/5789074899285883862.tmp
 * io/src/main/java/com/markus/java/file/test/dir.tmp
 * io/src/main/java/com/markus/java/file/test/bar/baz/bag/foo/File2.txt
 * io/src/main/java/com/markus/java/file/test/bar/baz/bag/foo/5458649986440731775.tmp
 * io/src/main/java/com/markus/java/file/test/bag/foo/bar/baz/File0.txt
 * io/src/main/java/com/markus/java/file/test/bag/foo/bar/baz/5568292198534480479.tmp
 * *******************
 * 2767500121528410890.tmp
 * 5789074899285883862.tmp
 * dir.tmp
 * 5458649986440731775.tmp
 * 5568292198534480479.tmp
 * *******************
 * 2767500121528410890.tmp
 * 5789074899285883862.tmp
 * 5458649986440731775.tmp
 * 5568292198534480479.tmp
 */