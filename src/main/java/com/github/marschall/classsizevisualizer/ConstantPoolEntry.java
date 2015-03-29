package com.github.marschall.classsizevisualizer;

import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.Comparator;

class ConstantPoolEntry {
  
  private final int start;
  private final int size;
  
  ConstantPoolEntry(int start, int size) {
    this.start = start;
    this.size = size;
  }

  int getSize() {
    return size;
  }
  
  String toString(byte[] byteCode) {
    return new String(byteCode, start, size, UTF_8);
  }
  
  static Comparator<ConstantPoolEntry> bySizeDescending() {
    return (a, b) -> Integer.compare(b.size, a.size);
  }

}
