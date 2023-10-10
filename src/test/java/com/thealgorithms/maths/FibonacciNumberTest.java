import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FibonacciNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10, 20, 30})
    public void testNthFibonacci(int n) {
        long result = FibonacciNumber.nthFibonacci(n);
        long expectedResult = FibonacciCalculator.calculateFibonacciUsingLoop(n);
        assertEquals(expectedResult, result);
    }
}

class FibonacciCalculator {

    public static long calculateFibonacciUsingLoop(int n) {
        if (n <= 1) {
            return n;
        }
        long prev = 0;
        long current = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + current;
            prev = current;
            current = next;
        }
        return current;
    }
}
