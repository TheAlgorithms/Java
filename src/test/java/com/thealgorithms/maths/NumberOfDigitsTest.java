package com.thealgorithms.maths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfDigitsTest {
    @Test
    public void testNumberOfDigits() {
        assertEquals(1, NumberOfDigits.numberOfDigits(1));
        assertEquals(2, NumberOfDigits.numberOfDigits(99));
        assertEquals(3, NumberOfDigits.numberOfDigits(175));
        assertEquals(4, NumberOfDigits.numberOfDigits(1000));
    }

    
}
