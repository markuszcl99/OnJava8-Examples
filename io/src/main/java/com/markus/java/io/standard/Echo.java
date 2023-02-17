package com.markus.java.io.standard;

import com.markus.java.io.TimeAbort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/13
 * @Description: 显示输入的每一行内容
 */
public class Echo {
  public static void main(String[] args) {
    TimeAbort abort = new TimeAbort(2);
    new BufferedReader(new InputStreamReader(System.in))
        .lines()
        .peek(ln -> abort.restart())
        .forEach(System.out::println);
    // 按 ctrl+z 结束或者2秒内不输入任何信息
  }
}
