package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMinTest {
  @Test
  public void test_from_max2min(){
    assertEquals(1, FindMin.findMin(new int[] {9, 7, 5, 3, 1}));
  }
  
  @Test
  public void test_from_min2max(){
    assertEquals(0, FindMin.findMin(new int[] {0, 192, 384, 576}));
  }
	
  @Test
  public void test_random(){
    assertEquals(0, FindMin.findMin(new int[] {0, 5, 2, 1}));
  }

  @Test
  public void test_id(){
    assertEquals(0, FindMin.findMin(new int[] {0, 0, 0, 0}));
  }

}
