package com.thealgorithms.maths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PerfectNumberTest{
    PerfectNumber perfectNumber = new PerfectNumber();
    @Test 
    public void TestPerfectNumber() {
        assertEquals(true, perfectNumber.isPerfectNumber(28));
        assertEquals(true, perfectNumber.isPerfectNumber(496));
    }

    @Test
    public void TestNotPerfectNumber() {
        assertEquals(false, perfectNumber.isPerfectNumber(29));
        assertEquals(false, perfectNumber.isPerfectNumber(497));
    }
}