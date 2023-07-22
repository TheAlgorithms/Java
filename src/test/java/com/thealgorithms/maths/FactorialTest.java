package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FactorialTest {

    @Test
    public void test() {
        assertEquals(120, Factorial.factorial(5));
    }
}
