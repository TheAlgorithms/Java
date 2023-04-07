package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeonardoNumberTest {
    @Test
        //Negative Leonardo Number
    void leonardoNumberNegative() {
        assertThrows(ArithmeticException.class, ()-> LeonardoNumber.leonardoNumber(-1));
    }
    @Test
        //Zero Leonardo Number
    void leonardoNumberZero() {
        assertEquals(1, LeonardoNumber.leonardoNumber(0));
    }
    @Test
        //First Leonardo Number
    void leonardoNumberOne() {
        assertEquals(1, LeonardoNumber.leonardoNumber(1));
    }
    @Test
        //Fifth Leonardo Number
    void leonardoNumberFive() {
        assertEquals(15, LeonardoNumber.leonardoNumber(5));
    }
    @Test
        //Twentieth Leonardo Number
    void leonardoNumberTwenty() {
        assertEquals(21891 , LeonardoNumber.leonardoNumber(20));
    }
}
