package com.github.marschall.classsizevisualizer;

import java.io.PrintStream;

public class ClassSizePrinter {

  public void print(ClassInformation information, PrintStream writer) {
    writer.printf("class size: %d%n", information.getSize());
  }
  
}
