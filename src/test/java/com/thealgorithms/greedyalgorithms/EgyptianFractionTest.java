package com.thealgorithms.greedyalgorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class EgyptianFractionTest {
    @Test
    public void testGetEgyptianFraction() {
        EgyptianFraction ef = new EgyptianFraction();
        List<String> result = ef.getEgyptianFraction(5, 6);
        assertEquals(List.of("1/2", "1/3"), result);
    }
}
