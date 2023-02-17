package com.markus.java.io.nio;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/16
 * @Description: 可用的字符集
 */
public class AvailableCharsets {
  public static void main(String[] args) {
    SortedMap<String, Charset> charsets = Charset.availableCharsets();
    for (String csName : charsets.keySet()) {
      System.out.print(csName);
      Iterator aliases = charsets.get(csName)
          .aliases() // Returns a set containing this charset's aliases.
          .iterator();
      if (aliases.hasNext()) {
        System.out.print(": ");
      }
      while (aliases.hasNext()) {
        System.out.print(aliases.next());
        if (aliases.hasNext()) {
          System.out.print(",");
        }
      }
      System.out.println();
    }
  }
}
