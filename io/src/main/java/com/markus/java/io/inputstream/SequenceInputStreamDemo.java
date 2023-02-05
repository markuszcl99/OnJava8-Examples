package com.markus.java.io.inputstream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: markus
 * @date: 2023/2/5 2:50 PM
 * @Description: 构造其他流序列作为源的输入流演示
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SequenceInputStreamDemo {
    public static void main(String[] args) throws IOException {
        InputStream is1 = new ByteArrayInputStream(new byte[]{1, 2, 3, 4, 5});
        InputStream is2 = new ByteArrayInputStream(new byte[]{6, 7, 8, 9, 10});
        SequenceInputStream sequenceIs = new SequenceInputStream(is1, is2);

        List<Integer> bytes = new ArrayList<>();
        int temp;
        while ((temp = sequenceIs.read()) != -1) {
            bytes.add(temp);
        }
        System.out.println(bytes.size());
        for (Integer aByte : bytes) {
            System.out.print(aByte + " ");
        }
    }
}
/**
 * 10
 * 1 2 3 4 5 6 7 8 9 10
 * Process finished with exit code 0
 */