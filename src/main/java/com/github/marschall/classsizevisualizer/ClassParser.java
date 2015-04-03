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
      
    for (int i = 1; i < itemCount; i++) {
      // skip item 0 because reasons
      int start = reader.getItem(i);
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
    
 // visits the fields and methods
    int index = reader.header + 6;
    int interfacesCount = reader.readUnsignedShort(index);
    System.out.println("interfaces count: " + interfacesCount);
    index = reader.header + 8 + 2 * interfacesCount;
    int fieldsCount = reader.readUnsignedShort(index);
    System.out.println("fields count: " + fieldsCount);
    index += 2;
    
    List<Field> fields = new ArrayList<>(fieldsCount);
    for (int i = 0; i < fieldsCount; i++) {
      index = readField(index, reader);
    }
//    u += 2;
//    for (int i = readUnsignedShort(u - 2); i > 0; --i) {
//        u = readMethod(classVisitor, context, u);
//    }
    
    items.sort(Sized.bySizeDescending());
    return new ClassInformation(byteCode.length, byteCode, items, fields);
  }
  
  private int readField(int index, ClassReader reader) {
    String fieldName = reader.readUTF8(index + 2, new char[reader.getMaxStringLength()]);
    System.out.println("field name: " + fieldName);
    int attributesCount = reader.readUnsignedShort(index + 6);
    int currentIndex = index + 8;
    for (int i = 0; i < attributesCount; i++) {
      int attributeLength = reader.readInt(currentIndex + 2);
      currentIndex += attributeLength;
    }
    return currentIndex;
  }
  
  

}
