package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class perimeterTest {

    //Perimeter of polygon
    @Test
    void testcase1() {
        Assertions.assertEquals(20.0, Perimeter.perimeter_polygon(4, 5));
    }

    @Test
    void testcase2() {
        Assertions.assertEquals(30.0, Perimeter.perimeter_polygon(5, 6));
    }

    //Perimeter of Rectangle
    @Test
    void testcase3() {
        Assertions.assertEquals(18.0, Perimeter.perimeter_rectangle(4, 5));
    }

    @Test
    void testcase4() {
        Assertions.assertEquals(14.0, Perimeter.perimeter_rectangle(4, 3));
    }

    //Circumference of a circle
    @Test
    void testcase5() {
        Assertions.assertEquals(31.41592653589793, Perimeter.circumference(5));
    }

    @Test
    void testcase6() {
        Assertions.assertEquals(43.982297150257104, Perimeter.circumference(7));
    }
}
