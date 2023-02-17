package com.markus.java.io.standard;

import java.io.*;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/13
 * @Description: 标准I/O重定向
 */
public class Redirecting {
  public static void main(String[] args) {
    PrintStream console = System.out;
    try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("io/src/main/java/com/markus/java/io/standard/Redirecting.java"));
         PrintStream out = new PrintStream(
             new BufferedOutputStream(
                 new FileOutputStream("io/src/main/java/com/markus/java/io/standard/Redirecting.txt")));) {
      System.setIn(in);
      System.setOut(out);
      System.setErr(out);
      new BufferedReader(new InputStreamReader(System.in))
          .lines()
          .forEach(System.out::println);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      System.setOut(console);
    }
  }
}
