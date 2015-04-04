package com.github.marschall.classsizevisualizer;

import java.util.List;

class ClassInformation implements Sized {

  private final int size;
  private final List<ConstantPoolEntry> poolEntryies;
  private final byte[] byteCode;
  private final List<Member> fields;
  private final List<Member> methods;
  private final List<Member> attributes;

  ClassInformation(int size, byte[] byteCode, List<ConstantPoolEntry> poolEntryies,
      List<Member> fields, List<Member> methods, List<Member> attributes) {
    this.size = size;
    this.poolEntryies = poolEntryies;
    this.byteCode = byteCode;
    this.fields = fields;
    this.methods = methods;
    this.attributes = attributes;
  }

  List<ConstantPoolEntry> getPoolEntryies() {
    return this.poolEntryies;
  }

  List<Member> getFields() {
    return this.fields;
  }

  List<Member> getMethods() {
    return this.methods;
  }

  List<Member> getAttributes() {
    return this.attributes;
  }

  byte[] getByteCode() {
    return this.byteCode;
  }

  int getPoolSize() {
    return sumOfSizes(this.poolEntryies);
  }

  int getFieldsSize() {
    return sumOfSizes(this.fields);
  }

  int getMethodsSize() {
    return sumOfSizes(this.methods);
  }

  int getAttributesSize() {
    return sumOfSizes(this.attributes);
  }

  private static int sumOfSizes(List<? extends Sized> list) {
    return list.stream().mapToInt(e -> e.getSize()).sum();
  }

  public int getSize() {
    return size;
  }

}
