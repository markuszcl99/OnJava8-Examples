package com.markus.java.io.outputstream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author: markus
 * @date: 2023/2/5 4:10 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ByteArrayOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream byteArrayOs = new ByteArrayOutputStream();
        byte[] bytes = {1, 2, 3, 4, 5};
        byteArrayOs.write(bytes);

        byte[] memory = byteArrayOs.toByteArray();
        for (byte b : memory) {
            System.out.print(b + " ");
        }
    }
}
/**
 * 1 2 3 4 5
 * Process finished with exit code 0
 */