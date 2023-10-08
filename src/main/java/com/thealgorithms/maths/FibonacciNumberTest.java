import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FibonacciNumberTest {

    @Test
    public void testNthFibonacci() {
        // Create an instance of GenericFibonacciNumber
        FibonacciNumber<Integer> searcher = new FibonacciNumber<>()
        
        // Test case 1: Fibonacci number at position 0
        int result1 = 
            FibonacciNumber.nthFibonacci(0);
        assertEquals(0, result1);

        // Test case 2: Fibonacci number at position 1
        int result2 = 
            FibonacciNumber.nthFibonacci(1);
        assertEquals(1, result2);

        // Test case 3: Fibonacci number at position 2
        int result3 = 
            FibonacciNumber.nthFibonacci(2);
        assertEquals(1, result3);

        // Test case 4: Fibonacci number at position 5
        int result4 = 
            FibonacciNumber.nthFibonacci(5);
        assertEquals(5, result4);

        // Test case 5: Fibonacci number at a large position (e.g., 10)
        int result5 = 
            FibonacciNumber.nthFibonacci(10);
        assertEquals(55, result5);

        // Test case 6: Fibonacci number at a negative position (should return -1)
        int result6 = 
            FibonacciNumber.nthFibonacci(-3);
        assertEquals(-1, result6);
    }
}
