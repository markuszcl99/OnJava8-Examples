package com.markus.java.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/13
 * @Description:
 */
public class GetChannel {
  private static String name = "io/src/main/java/com/markus/java/io/nio/data.txt";
  private static final int BSIZE = 1024;

  public static void main(String[] args) {
    // 写文件
    try (
        FileChannel fc = new FileOutputStream(name).getChannel()
    ) {
      fc.write(ByteBuffer.wrap("Some text ".getBytes()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // 添加到文件末尾
    try (
        FileChannel fc = new RandomAccessFile(name, "rw").getChannel();
    ) {
      // 移动到文件末尾
      fc.position(fc.size());
      fc.write(ByteBuffer.wrap("Some more".getBytes()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // 读文件
    try (
        FileChannel fc = new FileInputStream(name).getChannel()
    ) {
      ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
      fc.read(buffer);
      // 限制位置为当前position
      // 将position归0
      buffer.flip();
      while (buffer.hasRemaining()) {
        // buffer.get() 读取当前位置的字节 并且 将当前位置递增
        // System.out.write 向标准输出流中写入数据
        System.out.write(buffer.get());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // 刷新标准输出流 这样控制台才能有数据打印
    System.out.flush();
  }
}
