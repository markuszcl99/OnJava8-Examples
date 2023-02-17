package com.markus.java.io.nio;

import java.nio.ByteBuffer;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/16
 * @Description: 获取基本类型
 */
public class GetData {
  private static final int BSIZE = 1024;

  public static void main(String[] args) {
    // 分配空间后，会自动清零
    ByteBuffer bb = ByteBuffer.allocate(BSIZE);
    int i = 0;
    while (i++ < bb.limit()) {
      if (bb.get() != 0) {
        System.out.println("nonzero");
      }
    }
    System.out.println("i= " + i);
    // 准备写 char type
    bb.rewind();
    bb.asCharBuffer().put("Markus");
    char c;
    while ((c = bb.getChar()) != 0) {
      System.out.print(c + " ");
    }
    System.out.println();
    // short type
    bb.rewind();
    bb.asShortBuffer().put((short) 471142);
    System.out.println(bb.getShort());
    // int type
    bb.rewind();
    bb.asIntBuffer().put(99471142);
    System.out.println(bb.getInt());
    // long type
    bb.rewind();
    bb.asLongBuffer().put(99471142);
    System.out.println(bb.getLong());
    // float type
    bb.rewind();
    bb.asFloatBuffer().put(99471142);
    System.out.println(bb.getFloat());
    // double type
    bb.rewind();
    bb.asDoubleBuffer().put(99471142);
    System.out.println(bb.getDouble());
  }
}
