package com.markus.onjava.concurrent.stream;

import java.util.Arrays;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/24
 * @Description:
 */
public class Summing2 {

  static long basicSum(long[] la) {
    long sum = 0;
    int size = la.length;
    for (int i = 0; i < size; i++) {
      sum += la[i];
    }
    return sum;
  }

  // 接近内存溢出的最大值
  public static final int SZ = 350_000_000;

  /**
   * 高斯公式
   */
  public static final long CHECK = (long) SZ * ((long) SZ + 1) / 2;

  public static void main(String[] args) {
    System.out.println(CHECK);
    long[] la = new long[SZ + 1];
    Arrays.parallelSetAll(la, i -> i);
    Summing.timeTest("Array Stream Sum", CHECK, () -> Arrays.stream(la).sum());
    Summing.timeTest("Parallel", CHECK, () -> Arrays.stream(la).parallel().sum());
    Summing.timeTest("BasicSum", CHECK, () -> basicSum(la));
    Summing.timeTest("parallelPrefix", CHECK, () -> {
      Arrays.parallelPrefix(la, Long::sum);
      return la[la.length - 1];
    });
  }
}
