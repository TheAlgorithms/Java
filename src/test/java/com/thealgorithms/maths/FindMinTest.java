package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindMinTest {
  @Test public void validPartition() {
      //Valid Partition
  	Assertions.assertSame(1, FindMin.findMin(new int[] {1, 3, 5, 7, 9}));
  }
  
  @Test public void invalidPartition1() {
      //Invalid Partition
  	Assertions.assertNotSame(3, FindMin.findMin(new int[] {1, 3, 5, 7, 9}));
  }
  
  @Test public void invalidPartition2() {
      //Invalid Partition
  	Assertions.assertNotSame(-2, FindMin.findMin(new int[] {1, 3, 5, 7, 9}));
  }
  
  @Test public void invalidPartition3() {
      //Invalid Partition
  	Assertions.assertNotSame(null, FindMin.findMin(new int[] {1, 3, 5, 7, 9}));
  }
}
