package com.markus.java.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author: markus
 * @date: 2023/2/5 4:58 PM
 * @Description: RandomAccessFile使用演示
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(new File("/Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/resources/RandomAccessFile.txt"), "rw");
        raf.writeDouble(1d);
        raf.writeDouble(2d);
        raf.writeInt(1);
        raf.writeUTF("Hello,RandomAccessFile");
        raf.close();

        raf = new RandomAccessFile(new File("/Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/resources/RandomAccessFile.txt"), "rw");
        System.out.println(raf.readDouble());
        raf.seek(16);
        System.out.println(raf.readInt());
        System.out.println(raf.readUTF());
        System.out.println(raf.getFilePointer());
        raf.close();
    }
}
/**
 * 1.0
 * 1
 * Hello,RandomAccessFile
 * 44
 *
 * Process finished with exit code 0
 */