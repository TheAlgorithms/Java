import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class FibonacciTest {
    @Test
    public void checkValueAtZero() {
        assertEquals(BigInteger.ZERO, Fibonacci.calFib(0));
    }

    @Test
    public void checkValueAtOne() {
        assertEquals(BigInteger.ONE, Fibonacci.calFib(1));
    }

    @Test
    public void checkValueAtTwo() {
        assertEquals(BigInteger.ONE, Fibonacci.calFib(2));
    }

    @Test
    public void checkRecurrenceRelation() {
        for (int i = 0; i < 100; ++i) {
            assertEquals(Fibonacci.calFib(i + 2), Fibonacci.calFib(i + 1).add(Fibonacci.calFib(i)));
        }
    }

    @Test
    public void checkNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> { Fibonacci.calFib(-1); });
    }
}
