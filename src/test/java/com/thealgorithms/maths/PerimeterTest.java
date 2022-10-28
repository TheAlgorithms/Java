package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PerimeterTest {

    //Perimeter of polygon
    @Test
    void testcase1() {
        assertEquals(20.0, Perimeter.perimeterPolygon(4, 5));
    }

    @Test
    void testcase2() {
        assertEquals(30.0, Perimeter.perimeterPolygon(5, 6));
    }

    //Perimeter of rectangle
    @Test
    void testcase3() {
        assertEquals(18.0, Perimeter.perimeterRectangle(4, 5));
    }

    @Test
    void testcase4() {
        assertEquals(14.0, Perimeter.perimeterRectangle(4, 3));
    }

    //Circumference of a circle
    @Test
    void testcase5() {
        assertEquals(31.41592653589793, Perimeter.perimeterCircle(5));
    }

    @Test
    void testcase6() {
        assertEquals(43.982297150257104, Perimeter.perimeterCircle(7));
    }
    
    //Perimeter of triangle
    @Test
    void testcase7() {
        assertEquals(18.0, Perimeter.perimeterTriangle(6, 6, 6));
    }

    @Test
    void testcase8() {
        assertEquals(26.0, Perimeter.perimeterTriangle(6, 8, 12));
    }
    
    //Perimeter of trapezoid
    @Test
    void testcase9() {
        assertEquals(38.0, Perimeter.perimeterTrapezoid(4, 7, 12, 15));
    }

    @Test
    void testcase10() {
        assertEquals(19.0, Perimeter.perimeterTrapezoid(5, 7, 3, 4));
    }
}
