package com.github.marschall.classsizevisualizer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassSizeVisualizer {

  public static void main(String[] args) throws IOException {
    Path path = Paths.get(args[0]);
    InputStream stream = Files.newInputStream(path);
    ClassInformation information = new ClassParser().parse(new BufferedInputStream(stream));
    new ClassSizePrinter().print(information, System.out);

  }

}
