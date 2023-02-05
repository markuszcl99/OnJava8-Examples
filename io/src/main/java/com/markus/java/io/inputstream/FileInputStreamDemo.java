package com.markus.java.io.inputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: markus
 * @date: 2023/2/5 2:30 PM
 * @Description: 构造文件源的输入流演示
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/resources/file.txt");
        FileInputStream fileIs = new FileInputStream(file);
        long fileLen = file.length();
        int destLength = fileIs.available();
        byte[] dest = new byte[destLength];
        int readLen = fileIs.read(dest);
        System.out.println(fileLen);
        System.out.println(destLength);
        System.out.println(readLen);

        for (byte b : dest) {
            System.out.print(b + " ");
        }
    }
}