package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PerimeterTest {

    // Perimeter of Regular polygon
    @Test
    void testcase1() {
        Assertions.assertEquals(20.0, Perimeter.perimeterRegularPolygon(4, 5));
    }

    @Test
    void testcase2() {
        Assertions.assertEquals(30.0, Perimeter.perimeterRegularPolygon(5, 6));
    }

    // Perimeter of Rectangle
    @Test
    void testcase3() {
        Assertions.assertEquals(18.0, Perimeter.perimeterRectangle(4, 5));
    }

    @Test
    void testcase4() {
        Assertions.assertEquals(14.0, Perimeter.perimeterRectangle(4, 3));
    }

    // Circumference/Perimeter of a circle
    @Test
    void testcase5() {
        Assertions.assertEquals(31.41592653589793, Perimeter.perimeterCircle(5));
    }

    @Test
    void testcase6() {
        Assertions.assertEquals(43.982297150257104, Perimeter.perimeterCircle(7));
    }
  
    // Perimeter of Irregular polygon
    @Test
    void testcase7() {
        Assertions.assertEquals(12.0, Perimeter.perimeterIrregularPolygon(4, 5, 3));
    }

    @Test
    void testcase8() {
        Assertions.assertEquals(21.0, Perimeter.perimeterIrregularPolygon(3, 4, 5, 3, 6));
    }
}
