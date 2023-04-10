package com.markus.java.blog.bio.one;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: markus
 * @date: 2023/4/10 10:29 PM
 * @Description: 服务端
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Server {
    public static void main(String[] args) {
        try {
            // 1. 创建一个ServerSocket
            ServerSocket ss = new ServerSocket(9999);
            System.out.println("=====服务端建立=====");
            while (true) {
                // 2. 监听客户端连接请求
                Socket socket = ss.accept();// 这里是阻塞的，直到有客户端socket连接
                System.out.println("=====监听到Socket，开始进行通信=====");
                // 3. 监听到一个socket连接后，创建一个线程去处理这个socket上的通信
                new ServerSocketReader(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
