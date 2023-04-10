package com.markus.java.blog.bio.one;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: markus
 * @date: 2023/4/10 10:30 PM
 * @Description: 客户端
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 1. 创建一个客户端socket
        Socket socket = new Socket("127.0.0.1", 9999);
        // 2. 获取socket的输出流
        OutputStream os = socket.getOutputStream();
        PrintWriter print = new PrintWriter(os);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("请说: ");
            String msg = scanner.nextLine();
            print.println(msg);
            print.flush();
        }
    }
}
