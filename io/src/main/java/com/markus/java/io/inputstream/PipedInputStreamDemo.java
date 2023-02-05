package com.markus.java.io.inputstream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author: markus
 * @date: 2023/2/5 2:42 PM
 * @Description: 构造管道源的输入流演示
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class PipedInputStreamDemo {
    public static void main(String[] args) throws IOException {
        PipedOutputStream pipedOs = new PipedOutputStream();
        PipedInputStream pipedIs = new PipedInputStream(pipedOs);

        byte[] source = {1, 2, 3, 4, 5};
        pipedOs.write(source);

        int destLen = pipedIs.available();
        byte[] dest = new byte[destLen];
        int readLen = pipedIs.read(dest);
        System.out.println(destLen);
        System.out.println(readLen);

        for (byte b : dest) {
            System.out.print(b + " ");
        }
    }
}
/**
 * 5
 * 5
 * 1 2 3 4 5
 * Process finished with exit code 0
 */