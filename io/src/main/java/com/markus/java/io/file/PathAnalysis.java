package com.markus.java.io.file;

import com.markus.java.io.file.constant.FileConstant;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/8
 * @Description: 路径分析
 */
public class PathAnalysis {
    static void say(String id, Object result) {
        System.out.println(id + ": ");
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("os.name"));
        Path p = Paths.get(FileConstant.CURRENT_BASE_PATH, "/PathAnalysis.java");
        say("Exists", Files.exists(p));
        say("Directory", Files.isDirectory(p));
        say("Executable", Files.isExecutable(p));
        say("Readable", Files.isReadable(p));
        say("RegularFile", Files.isRegularFile(p));
        say("Writable", Files.isWritable(p));
        say("notExists", Files.notExists(p));
        say("Hidden", Files.isHidden(p));
        say("size", Files.size(p));
        // 存储文件。它表示存储池、设备、分区、卷、具体文件系统或其他特定于实现的文件存储方式。
        // 可以提供一组文件存储属性的只读或可更新视图
        say("FileStore", Files.getFileStore(p));
        say("LastModified", Files.getLastModifiedTime(p));
        say("Owner", Files.getOwner(p));
        say("ContentType", Files.probeContentType(p));
        say("SymbolicLink", Files.isSymbolicLink(p));
        if (Files.isSymbolicLink(p)) {
            // SymbolicLink 意为符号链接，是一类特殊文件，它包含一条以绝对路径或者相对路径的形式指向其他文件或者目录的引用
            // 它有点类似于Windows下的快捷方式
            say("SymbolicLink", Files.readSymbolicLink(p));
        }
        if (FileSystems.getDefault().supportedFileAttributeViews().contains("posix")) {
            // posix 意为可移植操作系统接口(Portable Operating System Interface of UNIX),
            // 它是IEEE<电气与电子工程师协会（Institute of Electrical and Electronics Engineers）>
            // 为要在各种UNIX操作系统上运行的软件而定义的一系列API标准的总称
            say("PosixFilePermissions", Files.getPosixFilePermissions(p));
        }
    }
}
