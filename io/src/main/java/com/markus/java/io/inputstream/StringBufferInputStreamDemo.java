package com.markus.java.io.inputstream;

import java.io.IOException;
import java.io.StringBufferInputStream;

/**
 * @author: markus
 * @date: 2023/2/5 2:24 PM
 * @Description: 构造字符源的输入流演示
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class StringBufferInputStreamDemo {
    public static void main(String[] args) throws IOException {
        String text = "Hello,IO";
        StringBufferInputStream stringIs = new StringBufferInputStream(text);
        int destLength = stringIs.available();
        byte[] dest = new byte[destLength];
        int len = stringIs.read(dest);
        System.out.println(len);
        for (byte b : dest) {
            System.out.print(b + " ");
        }
    }
}
