package com.markus.java.io.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/17
 * @Description: 关于视图缓冲区
 */
public class IntBufferDemo {
  private static final int BSIZE = 1024;

  public static void main(String[] args) {
    ByteBuffer bb = ByteBuffer.allocate(BSIZE);
    IntBuffer ib = bb.asIntBuffer();
    // 保持int数组
    ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
    // 通过绝对地址读写
    System.out.println(ib.get(3));
    ib.put(3, 1811);
    // 在回退缓冲区之前设置新的限制
    ib.flip();
    while (ib.hasRemaining()) {
      int i = ib.get();
      System.out.println(i);
    }
  }
}
