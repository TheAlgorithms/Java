package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EgyptianFractionTest {
    EgyptianFraction ef = new EgyptianFraction();

    @Test
    public void testGetEgyptianFraction_NormalCase() {
        List<String> result = ef.getEgyptianFraction(5, 6);
        assertEquals(List.of("1/2", "1/3"), result);
    }

    @Test
    public void testGetEgyptianFraction_SimpleFraction() {
        List<String> result = ef.getEgyptianFraction(1, 2);
        assertEquals(List.of("1/2"), result);
    }

    @Test
    public void testGetEgyptianFraction_WholeNumber() {
        List<String> result = ef.getEgyptianFraction(2, 1);
        assertEquals(List.of("1/1", "1/2", "1/3", "1/6"), result); // Example output
    }

    @Test
    public void testGetEgyptianFraction_OneOverOne() {
        List<String> result = ef.getEgyptianFraction(1, 1);
        assertEquals(List.of("1/1"), result);
    }

    @Test
    public void testGetEgyptianFraction_OneOverThree() {
        List<String> result = ef.getEgyptianFraction(1, 3);
        assertEquals(List.of("1/3"), result);
    }
}
