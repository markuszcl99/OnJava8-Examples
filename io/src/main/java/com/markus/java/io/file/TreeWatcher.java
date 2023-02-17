package com.markus.java.io.file;

import com.markus.java.io.file.constant.FileConstant;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/10
 * @Description:
 */
public class TreeWatcher {
    static void watchDir(Path dir) {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            dir.register(watcher, ENTRY_DELETE);
            Executors.newSingleThreadScheduledExecutor().submit(() -> {
                try {
                    WatchKey key = watcher.take();
                    for (WatchEvent<?> evt : key.pollEvents()) {
                        System.out.println("evt.context(): " + evt.context() +
                                "\nevt.count(): " + evt.count() +
                                "\nevt.kind(): " + evt.kind());
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Directories.refreshTestDir();
        Directories.populateTestDir();
        Files.walk(Paths.get(FileConstant.CURRENT_BASE_PATH, "test"))
                .filter(Files::isDirectory)
                .forEach(TreeWatcher::watchDir);
        PathWatcher.delTxtFiles();
    }
}
/** 控制台
 * deleting io/src/main/java/com/markus/java/file/test/foo/bar/baz/bag/File3.txt
 * deleting io/src/main/java/com/markus/java/file/test/baz/bag/foo/bar/File1.txt
 * deleting io/src/main/java/com/markus/java/file/test/bar/baz/bag/foo/File2.txt
 * deleting io/src/main/java/com/markus/java/file/test/bag/foo/bar/baz/File0.txt
 * evt.context(): File1.txt
 * evt.count(): 1
 * evt.kind(): ENTRY_DELETE
 * evt.context(): File0.txt
 * evt.count(): 1
 * evt.kind(): ENTRY_DELETE
 */