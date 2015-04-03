package com.github.marschall.classsizevisualizer;

import static java.nio.charset.StandardCharsets.UTF_8;

class ConstantPoolEntry implements Sized {
  
  private final int start;
  private final int size;
  
  ConstantPoolEntry(int start, int size) {
    this.start = start;
    this.size = size;
  }

  public int getSize() {
    return size;
  }
  
  String toString(byte[] byteCode) {
    return new String(byteCode, start, size, UTF_8);
  }
  
}
