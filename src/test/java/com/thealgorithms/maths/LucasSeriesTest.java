package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LucasSeriesTest {
    @Test
        //Use very first number in Lucas Series
    void lucasSeriesTwo() {
        //Lucas number 1
        assertEquals(2, LucasSeries.lucasSeries(1));
        assertEquals(2, LucasSeries.lucasSeriesIteration(1));
    }
    @Test
        //Use second number in Lucas Series
    void lucasSeriesOne() {
        //Lucas number 2
        assertEquals(1, LucasSeries.lucasSeries(2));
        assertEquals(1, LucasSeries.lucasSeriesIteration(2));
    }
    @Test
        //Use Seventh number in Lucas Series
    void lucasSeriesSeven() {
        //Lucas number 5
        assertEquals(7, LucasSeries.lucasSeries(5));
        assertEquals(7, LucasSeries.lucasSeriesIteration(5));
    }
    @Test
        //Use Eleventh number in Lucas Series
    void lucasSeriesEleven() {
        //Lucas number 5
        assertEquals(123, LucasSeries.lucasSeries(11));
        assertEquals(123, LucasSeries.lucasSeriesIteration(11));
    }
}
