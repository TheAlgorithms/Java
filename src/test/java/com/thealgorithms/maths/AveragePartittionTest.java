package com.algorithms.maths;

public class AveragePartitionTesting {
  // Test with double array
  public static void TestDoubleArray {
    assert Math.abs(average(new double[]{1, 2, 3, 4, 5, 6, 7, 8}) - 3.5) < SMALL_VALUE;
  }  
  
  // Test with int array
  public static void TestIntArray {
    int[] array = {2, 3, 10};
    assert average(array) == 5;
  }
}
