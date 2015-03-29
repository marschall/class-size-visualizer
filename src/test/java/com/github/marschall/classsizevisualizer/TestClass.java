package com.github.marschall.classsizevisualizer;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
  
  private final double d = Math.random();
  
  private static final List<Object> LIST;
  
  static {
    LIST = new ArrayList<>();
  }
  
  public TestClass() {
    super();
  }

  public static int getListSize() {
    return LIST.size();
  }
  
  @Override
  public String toString() {
    return Double.toString(d);
  }
  
  static void lamdba() {
    LIST.stream().forEach(System.out::println);
  }
  
  public void runPrintln() {
    Thread thread = new Thread(new Runnable() {
      
      @Override
      public void run() {
        System.out.println("run");
      }
    });
    thread.start();
  }
  
  public static class InnerClass {
    
  }
  
}
