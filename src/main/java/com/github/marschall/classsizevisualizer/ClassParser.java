package com.github.marschall.classsizevisualizer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;

class ClassParser {
  
  private static final int CONSTANT_Utf8 = 1;
  
  ClassInformation parse(InputStream input) throws IOException {
    ClassReader reader;
    try {
      reader = new ClassReader(input);
    } finally {
      input.close();
    }
    byte[] byteCode = reader.b;
    int itemCount = reader.getItemCount();
    List<ConstantPoolEntry> items = new ArrayList<>(itemCount);
      
    for (int i = 0; i < itemCount; i++) {
      int start = reader.getItem(i);
      if (start == 0) {
        continue;
      }
      int tag = byteCode[start -1];
      switch (tag) {
        case CONSTANT_Utf8:
          int size = reader.readUnsignedShort(start);
          items.add(new ConstantPoolEntry(start + 2, size));
          break;
        default:
          break;
      }
    }
    items.sort(ConstantPoolEntry.bySizeDescending());
    return new ClassInformation(byteCode.length, items, byteCode);
  }

}
