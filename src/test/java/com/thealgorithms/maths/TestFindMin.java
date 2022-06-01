package com.thealgorithms.maths

import org.junit.juniper.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFindMind {
  @Test
  public void test1(){
    assertEquals(1, FindMin.findMin(new int[] {1, 3, 5, 7, 9}));
  }
  
  @Test
  public void test2(){
    assertEquals(0, FindMin.findMin(new int[] {0, 192, 384, 576}));
  }
}
