package com.markus.java.io.reader_writer;

import java.io.*;

/**
 * @author: markus
 * @date: 2023/2/5 4:40 PM
 * @Description: FileReader、FileWriter使用示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class FileReaderAndWriterDemo {
    public static final String PATH_NAME = "/Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/resources/file_writer.txt";

    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter(PATH_NAME);
        fw.getEncoding();
        fw.write("Hello,IO");
        fw.flush();
        fw.close();

        FileInputStream fileOs = new FileInputStream(new File(PATH_NAME));
        int len = fileOs.available();
        InputStreamReader fr = new InputStreamReader(fileOs);
        char[] chars = new char[len];
        int readLen = fr.read(chars);
        fr.close();
        fileOs.close();

        System.out.println(new String(chars));
    }
}
/**
 * Hello,IO
 *
 * Process finished with exit code 0
 */