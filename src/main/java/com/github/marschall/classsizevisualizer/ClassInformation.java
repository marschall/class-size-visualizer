package com.github.marschall.classsizevisualizer;

import java.util.List;

class ClassInformation {

  private final int size;
  private final List<ConstantPoolEntry> poolEntryies;
  private final byte[] byteCode;

  ClassInformation(int size, List<ConstantPoolEntry> poolEntryies, byte[] byteCode) {
    this.size = size;
    this.poolEntryies = poolEntryies;
    this.byteCode = byteCode;
  }

  List<ConstantPoolEntry> getPoolEntryies() {
    return poolEntryies;
  }

  byte[] getByteCode() {
    return byteCode;
  }

  int getPoolSize() {
    return poolEntryies.stream().mapToInt(e -> e.getSize()).sum();
  }

  int getSize() {
    return size;
  }

}
