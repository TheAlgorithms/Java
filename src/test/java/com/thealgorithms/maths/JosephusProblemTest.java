package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JosephusProblemTest {

    @Test
    void testJosephusProblem() {
        assertEquals(3, JosephusProblem.findTheWinner(5, 2));
        assertEquals(5, JosephusProblem.findTheWinner(6, 4));
    }
}
