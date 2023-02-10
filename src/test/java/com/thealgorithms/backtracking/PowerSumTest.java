package com.thealgorithms.backtracking;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PowerSumTest {

    @Test
    void testNumberZeroAndPowerZero() {
        PowerSum powerSum = new PowerSum();
        int result = powerSum.powSum(0, 0);
        assertEquals(1, result);
    }

    @Test
    void testNumberHundredAndPowerTwo() {
        PowerSum powerSum = new PowerSum();
        int result = powerSum.powSum(100, 2);
        assertEquals(3, result);
    }
  
    @Test
    void testNumberHundredAndPowerThree() {
        PowerSum powerSum = new PowerSum();
        int result = powerSum.powSum(100, 3);
        assertEquals(1, result);
    }
    
}
