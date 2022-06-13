package com.thealgorithms.others;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
    @Test
    public void test1(){
        Assertions.assertEquals(Triangle.determineType(0,1,1),"Not a Triangle");
    }@Test
    public void test2(){
        Assertions.assertEquals(Triangle.determineType(1,1,1),"Equilateral Triangle");
    }
    @Test
    public void test3(){
        Assertions.assertEquals(Triangle.determineType(5,4,4),"Isosceles Triangle");
    }
}
