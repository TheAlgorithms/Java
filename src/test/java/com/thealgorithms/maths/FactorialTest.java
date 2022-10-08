package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FactorialTest {

    @Test
    public void test() {
        Factorial fact = new Factorial();
        assertEquals(120, fact.factorial(5));
    }
}
