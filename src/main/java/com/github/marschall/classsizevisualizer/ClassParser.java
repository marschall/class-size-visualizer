package com.github.marschall.classsizevisualizer;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;

public class ClassParser {
  
  public ClassInformation parse(InputStream input) throws IOException {
    ClassReader reader;
    try {
      reader = new ClassReader(input);
    } finally {
      input.close();
    }
    byte[] byteCode = reader.b;
    return new ClassInformation(byteCode.length);
//    ClassNode classNode = new ClassNode(ASM5);
//    reader.accept(classNode, 0);
  }

}
