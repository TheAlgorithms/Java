package com.thealgorithms.maths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PerfectNumberTest{
    @Test
    //Valid partition
    public void TestPerfectNumber() {
        assertEquals(true, PerfectNumber.isPerfectNumber(6));
    }

    @Test
    //Invalid partition
    public void TestNotPerfectNumber() {
        assertEquals(false, PerfectNumber.isPerfectNumber(10));
    }
}

