package com.github.marschall.classsizevisualizer;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class ClassParserTest {

  @Test
  public void printTestClass() throws IOException {
    String name = TestClass.class.getName();
    ClassLoader classLoader = ClassParserTest.class.getClassLoader();
    InputStream stream = classLoader.getResourceAsStream(name.replace('.', '/') + ".class");
    ClassInformation information = new ClassParser().parse(stream);
    new ClassSizePrinter().print(information, System.out);
  }

}
