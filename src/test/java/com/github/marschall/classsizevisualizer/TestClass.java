package com.github.marschall.classsizevisualizer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

public class TestClass implements Serializable {
  
  @Resource(name = "java:comp/env/double")
  private final double d = Math.random();
  
  private static final List<Object> LIST;
  
  static {
    LIST = new ArrayList<>();
  }
  
  public TestClass() {
    super();
  }

  @PostConstruct
  public static int getListSize() {
    return LIST.size();
  }
  
  @Override
  public String toString() {
    return Double.toString(d);
  }
  
  static void lamdba() {
//    for (Object object : LIST) {
//      System.out.println(object);
//    }
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
