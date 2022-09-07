package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class perimeterTest {

    //Perimeter of polygon
    @Test
    void testcase1(){
        Assertions.assertEquals(20.0,Perimeter.perimeter_polygon(4,5));
    }
    @Test
    void testcase2(){
        Assertions.assertEquals(30.0,Perimeter.perimeter_polygon(5,6));
    }

    //Perimeter of Rectangle
    @Test
    void testcase3(){
        Assertions.assertEquals(20.0,Perimeter.perimeter_rectangle(4,5));
    }
    @Test
    void testcase4(){
        Assertions.assertEquals(12.0,Perimeter.perimeter_rectangle(4,3));
    }

    //Circumference of a circle
    @Test
    void testcase5(){
        Assertions.assertEquals(78.50,Perimeter.circumference(5));
    }
    @Test
    void testcase6(){
        Assertions.assertEquals(153.86,Perimeter.circumference(7));
    }
}
