package com.thealgorithms.maths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PerfectNumberTest{
    @Test
    //valid partition
    public void TestPerfectNumber() {
        assertEquals(true, PerfectNumber.isPerfectNumber(28));
    }

    @Test
    //invalid partition
    public void TestNotPerfectNumber() {
        assertEquals(false, PerfectNumber.isPerfectNumber(29));
    }
}