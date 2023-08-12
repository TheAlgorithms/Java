package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

 class IsEvenTest {
    @Test
    void testIsEven() {
        assertEquals(true, IsEven.isEven(2));
        assertEquals(true, IsEven.isEven(-12));
        assertEquals(false, IsEven.isEven(21));
    }
}
