package com.github.marschall.classsizevisualizer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
    int[] itemToIndex = new int[itemCount];
    Arrays.fill(itemToIndex, -1); // make sure we get an exception if we point to something that is not CONSTANT_Utf8_info
    List<ConstantPoolEntry> items = new ArrayList<>(itemCount);

    for (int i = 1; i < itemCount; i++) {
      // skip item 0 because reasons
      int start = reader.getItem(i);
      int tag = byteCode[start -1];
      switch (tag) {
        case CONSTANT_Utf8:
          int size = reader.readUnsignedShort(start);
          itemToIndex[i] = items.size();
          items.add(new ConstantPoolEntry(start + 2, size));
          break;
        default:
          break;
      }
    }

    // visits the fields and methods
    int index = reader.header + 6;
    int interfacesCount = reader.readUnsignedShort(index);
    index = reader.header + 8 + 2 * interfacesCount;
    int fieldsCount = reader.readUnsignedShort(index);
    index += 2;

    List<Member> fields = new ArrayList<>(fieldsCount);
    for (int i = 0; i < fieldsCount; i++) {
      MemberInfo fieldInfo = readMember(index, reader);
      int fieldSize = fieldInfo.size;
      fields.add(new Member(fieldSize, items.get(itemToIndex[fieldInfo.nameIndex])));
      index += fieldSize;
    }
    int methodsCount = reader.readUnsignedShort(index);
    index += 2;

    List<Member> methods = new ArrayList<>(methodsCount);
    for (int i = 0; i < methodsCount; i++) {
      MemberInfo methodInfo = readMember(index, reader);
      int methodSize = methodInfo.size;
      methods.add(new Member(methodSize, items.get(itemToIndex[methodInfo.nameIndex])));
      index += methodSize;
    }
    
    int attributesCount = reader.readUnsignedShort(index);
    index += 2;
    List<Member> attributes = new ArrayList<>(attributesCount);
    for (int i = 0; i < attributesCount; i++) {
      MemberInfo attributeInfo = readAttribute(index, reader);
      int attributeSize = attributeInfo.size;
      attributes.add(new Member(attributeSize, items.get(itemToIndex[attributeInfo.nameIndex])));
      index += attributeSize;
    }

    Comparator<Sized> bySizeDescending = Sized.bySizeDescending();
    items.sort(bySizeDescending);
    fields.sort(bySizeDescending);
    methods.sort(bySizeDescending);
    attributes.sort(bySizeDescending);
    return new ClassInformation(byteCode.length, byteCode, items, fields, methods, attributes);
  }

  private static MemberInfo readMember(int index, ClassReader reader) {
    int nameIndex = reader.readUnsignedShort(index + 2);
    int attributesCount = reader.readUnsignedShort(index + 6);
    int currentIndex = index + 8;
    for (int i = 0; i < attributesCount; i++) {
      int attributeLength = reader.readInt(currentIndex + 2);
      currentIndex += attributeLength + 6;
    }
    int size = currentIndex - index;
    return new MemberInfo(size, nameIndex);
  }
  
  private static MemberInfo readAttribute(int index, ClassReader reader) {
    int nameIndex = reader.readUnsignedShort(index);
    int attributeLength = reader.readInt(index + 2);
    int size = attributeLength + 6;
    return new MemberInfo(size, nameIndex);
  }

  static final class MemberInfo {
    final int size;
    final int nameIndex;

    MemberInfo(int size, int nameIndex) {
      this.size = size;
      this.nameIndex = nameIndex;
    }


  }

}
