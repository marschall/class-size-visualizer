package com.github.marschall.classsizevisualizer;


class Member implements Sized {

  private final int size;
  private final ConstantPoolEntry name;

  Member(int size, ConstantPoolEntry name) {
    this.size = size;
    this.name = name;
  }

  public int getSize() {
    return this.size;
  }

  String toString(byte[] byteCode) {
    return this.name.toString(byteCode);
  }

}
