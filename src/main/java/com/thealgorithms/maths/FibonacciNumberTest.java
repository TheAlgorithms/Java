import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FibonacciNumberTest {

    @Test
    public void testNthFibonacci() {
        // Test case 1: Fibonacci number at position 0
        Integer result1 = FibonacciNumber.nthFibonacci(0);
        assertEquals(Integer.valueOf(0), result1);

        // Test case 2: Fibonacci number at position 1
        Integer result2 = FibonacciNumber.nthFibonacci(1);
        assertEquals(Integer.valueOf(1), result2);

        // Test case 3: Fibonacci number at position 2
        Integer result3 = FibonacciNumber.nthFibonacci(2);
        assertEquals(Integer.valueOf(1), result3);

        // Test case 4: Fibonacci number at position 5
        Integer result4 = FibonacciNumber.nthFibonacci(5);
        assertEquals(Integer.valueOf(5), result4);

        // Test case 5: Fibonacci number at a large position (e.g., 10)
        Integer result5 = FibonacciNumber.nthFibonacci(10);
        assertEquals(Integer.valueOf(55), result5);

        // Test case 6: Fibonacci number at a negative position (should return -1)
        assertThrows(IllegalArgumentException.class, () -> { FibonacciNumber.nthFibonacci(-3); });
    }
}
