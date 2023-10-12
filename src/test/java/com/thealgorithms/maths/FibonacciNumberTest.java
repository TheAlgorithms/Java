import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

public class FibonacciNumberTest {

    @Test
    public void returnsCorrectValues() {
        for (int n = 0; n <= 92; ++n) {
            final var actual = FibonacciNumber.nthFibonacci(n);
            final var expected = Fibonacci.calFib(n);
            System.out.println("n = " + n);
            assertEquals(expected, actual);
        }
    }
}
