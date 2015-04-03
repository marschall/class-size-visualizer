package com.github.marschall.classsizevisualizer;

import java.io.PrintStream;

class ClassSizePrinter {

  void print(ClassInformation information, PrintStream writer) {
    byte[] byteCode = information.getByteCode();
    writer.printf("total: %d bytes%n", information.getSize());
    writer.printf("constant pool: %d bytes%n", information.getPoolSize());
    for (ConstantPoolEntry entry : information.getPoolEntryies()) {
      writer.printf("    %s %d bytes%n", entry.toString(byteCode), entry.getSize());
    }
    writer.printf("fields pool: %d bytes%n", information.getFieldsSize());
    for (Field field : information.getFields()) {
      writer.printf("    %s %d bytes%n", field.toString(byteCode), field.getSize());
    }
  }
  
}
