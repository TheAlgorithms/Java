package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IsEvenTest {

    @Test
    void isEven() {
        assertTrue(IsEven.isEven(10));
        assertFalse(IsEven.isEven(19));
        assertTrue(IsEven.isEven(776));
        assertFalse(IsEven.isEven(1001));
    }
}