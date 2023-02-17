package com.markus.java.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/14
 * @Description:
 * args: io/src/main/java/com/markus/java/io/nio/TransferTo.java io/src/main/java/com/markus/java/io/nio/TransferTo.txt
 */
public class TransferTo {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("arguments: sourceFile destFile");
      System.exit(1);
    }
    try (
        FileChannel in = new FileInputStream(args[0]).getChannel();
        FileChannel out = new FileOutputStream(args[1]).getChannel();
    ) {
      in.transferTo(0, in.size(), out);
      // 或者使用
      // out.transferFrom(in,0,in.size());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
