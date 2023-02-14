package com.markus.java.file;

import com.markus.java.file.constant.FileConstant;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/7
 * @Description:
 */
public class PathInfo {

    static void show(String id, Object p) {
        System.out.println(id + p);
    }

    static void info(Path path) {
        show("toString: \n", path);
        // 路径是否存在
        show("Exists: ", Files.exists(path));
        // 是否是常规文件，也就是我们常规意义上的文件，例如.txt .png等，而非目录
        show("RegularFile: ", Files.isRegularFile(path));
        // 是否是目录
        show("Directory: ", Files.isDirectory(path));
        // 展示绝对路径
        show("Absolute: ", path.toAbsolutePath());
        // 展示文件名(最后一级)
        show("FileName: ", path.getFileName());
        // 展示父路径
        show("Parent: ", path.getParent());
        // 展示根路径
        show("Root: ", path.getRoot());
        System.out.println("****************************************************************************************");
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        info(FileSystems.getDefault().getPath(""));
        info(Paths.get("io/src/main/resources", "file", "file.txt"));
        Path path = Paths.get(FileConstant.CURRENT_BASE_PATH, "/PathInfo.java");
        info(path);
        Path ap = path.toAbsolutePath();
        info(ap);
        info(ap.getParent());
        try {
            info(ap.toRealPath());
        } catch (IOException e) {
            System.out.println(e);
        }
        URI uri = path.toUri();
        System.out.println("URI:\n" + uri);
        Path pathUri = Paths.get(uri);
        System.out.println(Files.exists(pathUri));
        // 不要被骗了
        File f = ap.toFile();
    }
}
/** 输出
 * Mac OS X
 * toString:
 *
 * Exists: true
 * RegularFile: false
 * Directory: true
 * Absolute: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples
 * FileName:
 * Parent: null
 * Root: null
 * ****************************************************************************************
 * toString:
 * io/src/main/resources/file/file.txt
 * Exists: false
 * RegularFile: false
 * Directory: false
 * Absolute: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/resources/file/file.txt
 * FileName: file.txt
 * Parent: io/src/main/resources/file
 * Root: null
 * ****************************************************************************************
 * toString:
 * io/src/main/java/com/markus/java/file/PathInfo.java
 * Exists: true
 * RegularFile: true
 * Directory: false
 * Absolute: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file/PathInfo.java
 * FileName: PathInfo.java
 * Parent: io/src/main/java/com/markus/java/file
 * Root: null
 * ****************************************************************************************
 * toString:
 * /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file/PathInfo.java
 * Exists: true
 * RegularFile: true
 * Directory: false
 * Absolute: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file/PathInfo.java
 * FileName: PathInfo.java
 * Parent: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file
 * Root: /
 * ****************************************************************************************
 * toString:
 * /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file
 * Exists: true
 * RegularFile: false
 * Directory: true
 * Absolute: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file
 * FileName: file
 * Parent: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java
 * Root: /
 * ****************************************************************************************
 * toString:
 * /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file/PathInfo.java
 * Exists: true
 * RegularFile: true
 * Directory: false
 * Absolute: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file/PathInfo.java
 * FileName: PathInfo.java
 * Parent: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file
 * Root: /
 * ****************************************************************************************
 * URI:
 * file:///Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file/PathInfo.java
 * true
 *
 * Process finished with exit code 0
 */