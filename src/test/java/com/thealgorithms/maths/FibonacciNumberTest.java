import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FibonacciNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10, 20, 30, 92})
    public void testNthFibonacci(int n) {
        BigInteger result = Fibonacci.nthFibonacci(n);
        BigInteger expectedResult = FibCalc.calFib(n); // Call the static method directly
        assertEquals(expectedResult, result);
    }
}
