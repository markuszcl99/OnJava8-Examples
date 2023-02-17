package com.markus.java.io.standard;

import java.io.PrintWriter;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/13
 * @Description:
 */
public class ChangeSystemOut {
  public static void main(String[] args) {
    PrintWriter out = new PrintWriter(System.out, true);
    out.println("Hello,World!");
//    out.flush();
  }
}
