package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedianTest {

    @Test
    public void testEvenElements() {
        assertEquals(5.5, Median.median(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testOddElements(){
        assertEquals(5, Median.median(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

}
