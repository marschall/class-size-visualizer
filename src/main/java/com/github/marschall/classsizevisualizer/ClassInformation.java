package com.github.marschall.classsizevisualizer;

import java.util.List;

class ClassInformation implements Sized {

  private final int size;
  private final List<ConstantPoolEntry> poolEntryies;
  private final byte[] byteCode;
  private final List<Field> fields;

  ClassInformation(int size, byte[] byteCode, List<ConstantPoolEntry> poolEntryies, List<Field> fields) {
    this.size = size;
    this.poolEntryies = poolEntryies;
    this.byteCode = byteCode;
    this.fields = fields;
  }

  List<ConstantPoolEntry> getPoolEntryies() {
    return poolEntryies;
  }
  
  List<Field> getFields() {
    return fields;
  }

  byte[] getByteCode() {
    return byteCode;
  }

  int getPoolSize() {
    return sumOfSizes(poolEntryies);
  }
  
  int getFieldsSize() {
    return sumOfSizes(fields);
  }
  
  private static int sumOfSizes(List<? extends Sized> list) {
    return list.stream().mapToInt(e -> e.getSize()).sum();
  }

  public int getSize() {
    return size;
  }

}
