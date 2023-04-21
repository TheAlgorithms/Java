package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeonardoNumberTest {
    @Test
    void leonardoNumberNegative() {
        assertThrows(ArithmeticException.class, ()-> LeonardoNumber.leonardoNumber(-1));
    }
    @Test
    void leonardoNumberZero() {
        assertEquals(1, LeonardoNumber.leonardoNumber(0));
    }
    @Test
    void leonardoNumberOne() {
        assertEquals(1, LeonardoNumber.leonardoNumber(1));
    }
    @Test
    void leonardoNumberFive() {
        assertEquals(15, LeonardoNumber.leonardoNumber(5));
    }
    @Test
    void leonardoNumberTwenty() {
        assertEquals(21891 , LeonardoNumber.leonardoNumber(20));
    }
}
