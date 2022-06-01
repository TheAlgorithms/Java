package com.thealgorithms.maths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PerfectNumberTest{
    @Test
    public void TestPerfectNumber() {
        PerfectNumber perfectNumber = new PerfectNumber();
        assertEquals(true, perfectNumber.isPerfectNumber(28));
        assertEquals(false, perfectNumber.isPerfectNumber(29));
        assertEquals(true, perfectNumber.isPerfectNumber(496));
        assertEquals(true, perfectNumber.isPerfectNumber(8128));
        assertEquals(false, perfectNumber.isPerfectNumber(8129));
    }
    
}