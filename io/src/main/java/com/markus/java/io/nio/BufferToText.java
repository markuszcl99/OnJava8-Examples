package com.markus.java.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/14
 * @Description:
 */
public class BufferToText {
  private static final int BSIZ = 1024;

  private static final String PATH = "io/src/main/java/com/markus/java/io/nio/";

  public static void main(String[] args) {
    try (
        FileChannel fc = new FileOutputStream(PATH + "BufferToText.txt").getChannel();
    ) {
      fc.write(ByteBuffer.wrap("Some text".getBytes()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ByteBuffer buffer = ByteBuffer.allocate(BSIZ);
    try (
        FileChannel fc = new FileInputStream(PATH + "BufferToText.txt").getChannel();
    ) {
      fc.read(buffer);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // 准备读
    buffer.flip();
    // 会乱码
    System.out.println(buffer.asCharBuffer());
    // 准备写
    buffer.rewind();
    // 用当前系统的默认字符集解码
    String encoding = System.getProperty("file.encoding");
    System.out.println("Decoded using " + encoding + ": " + Charset.forName(encoding).decode(buffer));
    // 对打印的内容进行编码
    try (
        FileChannel fc = new FileOutputStream(PATH + "data2.txt").getChannel()
    ) {
      fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // 再次试着读取
    buffer.clear();
    try (
        FileChannel fc = new FileInputStream(PATH + "data2.txt").getChannel()
    ) {
      fc.read(buffer);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // 准备读
    buffer.flip();
    System.out.println(buffer.asCharBuffer());
    // 用CharBuffer进行写操作
    buffer = ByteBuffer.allocate(24);
    buffer.asCharBuffer().put("Some text");
    try (
        FileChannel fc = new FileOutputStream(PATH + "data3.txt").getChannel()
    ) {
      fc.write(buffer);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // 读取并显示出来
    buffer.clear();
    try (
        FileChannel fc = new FileInputStream(PATH + "data3.txt").getChannel()
    ) {
      fc.read(buffer);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // 准备读
    buffer.flip();
    System.out.println(buffer.asCharBuffer());
  }
}
