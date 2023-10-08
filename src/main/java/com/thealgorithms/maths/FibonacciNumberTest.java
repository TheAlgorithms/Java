import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test; // Import the JUnit 5 Test annotation

public class FibonacciNumberTest {

    @Test
    public void testNthFibonacciZero() {
        int result = FibonacciNumber.nthFibonacci(0);
        assertEquals(0, result);
    }

    @Test
    public void testNthFibonacciOne() {
        int result = FibonacciNumber.nthFibonacci(1);
        assertEquals(1, result);
    }

    @Test
    public void testNthFibonacciTwo() {
        int result = FibonacciNumber.nthFibonacci(2);
        assertEquals(1, result);
    }

    @Test
    public void testNthFibonacciFive() {
        int result = FibonacciNumber.nthFibonacci(5);
        assertEquals(5, result);
    }

    @Test
    public void testNthFibonacciLarge() {
        int result = FibonacciNumber.nthFibonacci(10);
        assertEquals(55, result);
    }
}
