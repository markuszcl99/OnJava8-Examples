package com.markus.java.blog.nio.one;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author: markus
 * @date: 2023/4/11 10:27 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 1. 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2. 绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9000));
        // 3. 配置非阻塞
        serverSocketChannel.configureBlocking(false);

        // 4. 创建一个Selector轮询器
        Selector selector = Selector.open();
        // 4.1 将轮询器注册到管道中，并告知其监听"接收"事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 4.2 轮询所有与ServerSocketChannel建立连接的通道，该步骤是阻塞的。
        while (selector.select() > 0) {
            System.out.println("监听到一个事件");
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    // 可接收的事件
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 非阻塞模式
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 可读的事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = socketChannel.read(buffer)) > 0) {
                        // 初始化buffer到读就绪状态
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        // 写就绪状态
                        buffer.clear();
                    }
                }
                iterator.remove();
            }
        }

    }
}
