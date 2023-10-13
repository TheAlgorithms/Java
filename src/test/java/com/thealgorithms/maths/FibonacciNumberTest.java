import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class FibonacciNumberTest {

    @Test
    public void returnsCorrectValues() {
        for (int n = 0; n <= 70; ++n) {
            final var actual = FibonacciNumber.nthFibonacci(n);
            final var expected = Fibonacci.calFib(n);
            assertEquals(expected, actual);
        }
    }
}
