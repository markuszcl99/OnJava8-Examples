package com.markus.java.blog.bio.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author: markus
 * @date: 2023/4/10 10:32 PM
 * @Description: 服务端socket读取器
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ServerSocketReader extends Thread {
    private final Socket socket;

    public ServerSocketReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 1. 获取socket的输入流
            InputStream is = socket.getInputStream();
            // 2. 将输入字节流包装为缓冲字符流
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String msg;
            // reader.readLine()的动作是阻塞的，在客户端发送消息之前，服务端一直阻塞的
            while ((msg = reader.readLine()) != null) {
                // 3. 打印到控制台
                System.out.println("收到客户端消息: "+msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
