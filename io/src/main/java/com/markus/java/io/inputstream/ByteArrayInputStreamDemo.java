package com.markus.java.io.inputstream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author: markus
 * @date: 2023/2/5 1:48 PM
 * @Description: 基于字节数组数据源的输入类示例
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ByteArrayInputStreamDemo {
    public static void main(String[] args) throws IOException {
        byte[] source = {2, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteArrayInputStream bis = new ByteArrayInputStream(source);

        byte[] dest = new byte[source.length];
        // read 为读取缓冲区的总字节数，如果没有多余可读的字节则返回-1
        int read = bis.read(dest, 2, 5);
        System.out.println(read);
        for (byte b : dest) {
            System.out.print(b + " ");
        }
    }
}
