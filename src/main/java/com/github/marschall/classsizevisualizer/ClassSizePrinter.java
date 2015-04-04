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
    writer.printf("fields: %d bytes%n", information.getFieldsSize());
    for (Member field : information.getFields()) {
      writer.printf("    %s %d bytes%n", field.toString(byteCode), field.getSize());
    }
    writer.printf("methods: %d bytes%n", information.getMethodsSize());
    for (Member method : information.getMethods()) {
      writer.printf("    %s %d bytes%n", method.toString(byteCode), method.getSize());
    }
    writer.printf("attributes: %d bytes%n", information.getAttributesSize());
    for (Member attribute : information.getAttributes()) {
      writer.printf("    %s %d bytes%n", attribute.toString(byteCode), attribute.getSize());
    }
  }

}
