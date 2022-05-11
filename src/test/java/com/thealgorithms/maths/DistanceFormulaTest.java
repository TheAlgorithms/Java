package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistanceFormulaTest
{
  @Test
  void test1()
  {
    Assertions.assertEquals(DistanceFormula.distance(1,1,2,2), 1.4142135623730951);
  }
  @Test
  void test2()
  {
    Assertions.assertEquals(DistanceFormula.distance(1,3,8,0), 7.0710678118654755);
  }
  @Test
  void test3()
  {
    Assertions.assertEquals(DistanceFormula.distance(2.4,9.1,55.1,100), 110.91911467371168);
  }
  @Test
  void test4()
  {
    Assertions.assertEquals(DistanceFormula.distance(1000,13,20000,84), 19022.067605809836);
  }
}
