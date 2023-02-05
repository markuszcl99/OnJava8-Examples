package com.markus.java.io.inputstream;

import java.io.*;

/**
 * @author: markus
 * @date: 2023/2/5 3:14 PM
 * @Description: FilterInputStreamDemo
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class FilterInputStreamDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("===================DataInputStream======================");
        displayDataInputStream();
        System.out.println("===================BufferedInputStream====================");
        displayBufferedInputStream();
        System.out.println("===================NumberInputStream====================");
        displayLineNumberInputStream();
        System.out.println("===================PushbackInputStream====================");
        displayPushbackInputStream();
    }

    public static void displayDataInputStream() throws IOException {
        byte[] dataSource = new byte[]{1, 0, 0, 0, 10};
        ByteArrayOutputStream byteArrayOs = new ByteArrayOutputStream();
        DataOutputStream dataOs = new DataOutputStream(byteArrayOs);
        dataOs.write(dataSource);


        DataInputStream byteArrayIs = new DataInputStream(new ByteArrayInputStream(byteArrayOs.toByteArray()));
        System.out.println(byteArrayIs.readBoolean());
        System.out.println(byteArrayIs.readInt());

    }

    public static void displayLineNumberInputStream() {
//        LineNumberInputStream
    }

    public static void displayBufferedInputStream() throws IOException {
        BufferedInputStream bufferedIs = new BufferedInputStream(new FileInputStream(new File("/Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/io/inputstream/FilterInputStreamDemo.java")));
    }

    public static void displayPushbackInputStream() throws IOException {
        PushbackInputStream pushbackIs = new PushbackInputStream(new ByteArrayInputStream(new byte[]{1, 2, 3, 4}));

        System.out.println(pushbackIs.read());
        pushbackIs.unread(1);
        System.out.println(pushbackIs.read());
    }
}
/**
 * ===================DataInputStream======================
 * true
 * 10
 * ===================BufferedInputStream====================
 * ===================NumberInputStream====================
 * ===================PushbackInputStream====================
 * 1
 * 1
 *
 * Process finished with exit code 0
 */