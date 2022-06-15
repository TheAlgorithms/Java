package com.thealgorithms.maths;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;;

public class FindMinTest {
  @Test
  public void test1(){
    assertEquals(1, FindMin.findMin(new int[] {1, 3, 5, 7, 9}));
  }
  
  @Test
  public void test2(){
    assertEquals(0, FindMin.findMin(new int[] {0, 192, 384, 576}));
  }

  @Test
      //Valid PArtition
  public void validPartition(){
    assertEquals(0, FindMin.findMin(new int[] {0, 192, 384, 576}));
  }
  
  @Test public void invalidPartition1() {
      //Invalid Partition
  	assertNotSame(3, FindMin.findMin(new int[] {1, 3, 5, 7, 9}));
  }
  
  @Test public void invalidPartition2() {
      //Invalid Partition
  	assertNotSame(-2, FindMin.findMin(new int[] {1, 3, 5, 7, 9}));
  }
  
  @Test public void invalidPartition3() {
      //Invalid Partition
  	assertNotSame(null, FindMin.findMin(new int[] {1, 3, 5, 7, 9}));
  }
}
