package com.markus.java.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/14
 * @Description:
 * args: io/src/main/java/com/markus/java/io/nio/ChannelCopy.java io/src/main/java/com/markus/java/io/nio/ChannelCopy.txt
 */
public class ChannelCopy {
  private static final int BSIZE = 1024;

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("arguments: sourceFile destFile");
      System.exit(1);
    }
    try (
        FileChannel in = new FileInputStream(args[0]).getChannel();
        FileChannel out = new FileOutputStream(args[1]).getChannel();
    ) {
      ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
      while (in.read(buffer) != -1) {
        // 准备从缓冲区读取数据（准备写）
        buffer.flip();
        // 把缓冲区的数据写入输出管道
        out.write(buffer);
        // 清空缓冲区 用于再次写入（准备读）
        buffer.clear();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
