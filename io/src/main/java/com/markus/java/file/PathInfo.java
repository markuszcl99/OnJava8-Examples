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
//    /Users/zhangchenglong06/IdeaProjects/daily-record/src/main/resources/META-INF/file/file.txt
        info(Paths.get("/Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/resources/", "file", "file.txt"));
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
        File f = ap.toFile();
    }
}
