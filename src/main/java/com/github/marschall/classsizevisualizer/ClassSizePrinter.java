package com.github.marschall.classsizevisualizer;

import java.io.PrintStream;

class ClassSizePrinter {

  void print(ClassInformation information, PrintStream writer) {
    writer.printf("total: %d bytes%n", information.getSize());
    writer.printf("constant pool: %d bytes%n", information.getPoolSize());
    byte[] byteCode = information.getByteCode();
    for (ConstantPoolEntry entry : information.getPoolEntryies()) {
      writer.printf("    %s %d bytes%n", entry.toString(byteCode), entry.getSize());
    }
  }
  
}
