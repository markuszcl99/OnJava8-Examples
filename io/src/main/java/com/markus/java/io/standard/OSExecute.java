package com.markus.java.io.standard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/13
 * @Description: 运行操作系统命令，并将输出发送到控制台
 */
public class OSExecute {
  public static void command(String command) {
    boolean err = false;
    try {
      Process process = new ProcessBuilder(command.split(" ")).start();
      try (BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
           BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      ) {
        results.lines().forEach(System.out::println);
        err = errors.lines().peek(System.err::println).count() > 0;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    if (err) {
      throw new OSExecuteException("Errors executing " + command);
    }
  }
}
