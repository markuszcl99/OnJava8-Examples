package com.markus.java.io.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.markus.java.io.file.constant.FileConstant.CURRENT_BASE_PATH;


/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/9
 * @Description:
 */
public class Directories {
  static Path test = Paths.get(CURRENT_BASE_PATH, "test");
  static String sep = FileSystems.getDefault().getSeparator();
  static List<String> parts = Arrays.asList("foo", "bar", "baz", "bag");

  static Path makeVariant() {
    // 移动指定列表的元素 distance 位
    // 例如 [1, 2, 3, 4, 5] 执行Collections.rotate(list.subList(1,4),1)
    // 结果 [1, 4, 3, 4, 5]
    Collections.rotate(parts, 1);
    return Paths.get(CURRENT_BASE_PATH, "test", String.join(sep, parts));
  }

  static void refreshTestDir() throws IOException {
    // 如果文件存在则删除，否则创建目录
    if (Files.exists(test)) {
      RmDir.rmdir(test);
    }
    if (!Files.exists(test)) {
      Files.createDirectory(test);
    }
  }

  static void populateTestDir() throws IOException {
    for (int i = 0; i < parts.size(); i++) {
      Path variant = makeVariant();
      if (!Files.exists(variant)) {
        Files.createDirectories(variant);
        Files.copy(Paths.get(CURRENT_BASE_PATH, "Directories.java"), variant.resolve("File" + i + ".txt"));
        Files.createTempFile(variant, null, null);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    // 刷新测试目录
    refreshTestDir();
    // 在test路径下增加Hello.txt文件路径 并创建此路径文件
    Files.createFile(test.resolve("Hello.txt"));
    // 创建文件
    Path variant = makeVariant();
    try {
      // 创建一级目录的函数，当创建多级目录时会抛出文件不存在的异常
      Files.createDirectory(variant);
    } catch (Exception e) {
      System.out.println("Nope, that doesn't work." + e);
    }
    populateTestDir();
    Path tempDir = Files.createTempDirectory(test, "DIR_");
    Files.createTempFile(tempDir, "pre", ".non");
    // newDirectoryStream 只显示指定目录下的子目录或文件
    Files.newDirectoryStream(test).forEach(System.out::println);
    System.out.println("********************");
    // walk 可以浏览指定目录下的所有内容（目录树）
    Files.walk(test).forEach(System.out::println);
  }
}
/** 控制台
 * Nope, that doesn't work.java.nio.file.NoSuchFileException: io/src/main/java/com/markus/java/file/test/bag/foo/bar/baz
 * io/src/main/java/com/markus/java/file/test/DIR_260744175083605522
 * io/src/main/java/com/markus/java/file/test/foo
 * io/src/main/java/com/markus/java/file/test/baz
 * io/src/main/java/com/markus/java/file/test/bar
 * io/src/main/java/com/markus/java/file/test/bag
 * io/src/main/java/com/markus/java/file/test/Hello.txt
 * ********************
 * io/src/main/java/com/markus/java/file/test
 * io/src/main/java/com/markus/java/file/test/DIR_260744175083605522
 * io/src/main/java/com/markus/java/file/test/DIR_260744175083605522/pre7111318949801779914.non
 * io/src/main/java/com/markus/java/file/test/foo
 * io/src/main/java/com/markus/java/file/test/foo/bar
 * io/src/main/java/com/markus/java/file/test/foo/bar/baz
 * io/src/main/java/com/markus/java/file/test/foo/bar/baz/bag
 * io/src/main/java/com/markus/java/file/test/foo/bar/baz/bag/File2.txt
 * io/src/main/java/com/markus/java/file/test/foo/bar/baz/bag/2694156263661518806.tmp
 * io/src/main/java/com/markus/java/file/test/baz
 * io/src/main/java/com/markus/java/file/test/baz/bag
 * io/src/main/java/com/markus/java/file/test/baz/bag/foo
 * io/src/main/java/com/markus/java/file/test/baz/bag/foo/bar
 * io/src/main/java/com/markus/java/file/test/baz/bag/foo/bar/File0.txt
 * io/src/main/java/com/markus/java/file/test/baz/bag/foo/bar/6305328343447758907.tmp
 * io/src/main/java/com/markus/java/file/test/bar
 * io/src/main/java/com/markus/java/file/test/bar/baz
 * io/src/main/java/com/markus/java/file/test/bar/baz/bag
 * io/src/main/java/com/markus/java/file/test/bar/baz/bag/foo
 * io/src/main/java/com/markus/java/file/test/bar/baz/bag/foo/File1.txt
 * io/src/main/java/com/markus/java/file/test/bar/baz/bag/foo/5478658539929705751.tmp
 * io/src/main/java/com/markus/java/file/test/bag
 * io/src/main/java/com/markus/java/file/test/bag/foo
 * io/src/main/java/com/markus/java/file/test/bag/foo/bar
 * io/src/main/java/com/markus/java/file/test/bag/foo/bar/baz
 * io/src/main/java/com/markus/java/file/test/bag/foo/bar/baz/File3.txt
 * io/src/main/java/com/markus/java/file/test/bag/foo/bar/baz/5014150493260271159.tmp
 * io/src/main/java/com/markus/java/file/test/Hello.txt
 */