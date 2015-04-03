package com.github.marschall.classsizevisualizer;

import java.util.Comparator;

interface Sized {

  int getSize();
  
  static Comparator<Sized> bySizeDescending() {
    return (a, b) -> Integer.compare(b.getSize(), a.getSize());
  }
  
}
