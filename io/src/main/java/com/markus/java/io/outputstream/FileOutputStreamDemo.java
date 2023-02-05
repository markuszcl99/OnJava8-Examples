package com.markus.java.io.outputstream;

import java.io.*;

/**
 * @author: markus
 * @date: 2023/2/5 4:13 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class FileOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOs = new FileOutputStream(new File("/Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/resources/fileFromOutputStream.txt"));
        fileOs.write(new byte[]{1, 2, 3, 4, 5});

        FileInputStream fileIs = new FileInputStream(new File("/Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/resources/fileFromOutputStream.txt"));
        int read;
        while ((read = fileIs.read()) != -1) {
            System.out.print(read + " ");
        }
    }
}
/**
 * 1 2 3 4 5
 * Process finished with exit code 0
 */