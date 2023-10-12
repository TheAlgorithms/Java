import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class FibCalcTest {
    @Test
    public void checkValueAtZero() {
        assertEquals(BigInteger.ZERO, FibCalc.calFib(0));
    }

    @Test
    public void checkValueAtOne() {
        assertEquals(BigInteger.ONE, FibCalc.calFib(1));
    }

    @Test
    public void checkValueAtTwo() {
        assertEquals(BigInteger.ONE, FibCalc.calFib(2));
    }

    @Test
    public void checkRecurenceRelation() {
        for (int i = 0; i < 100; ++i) {
            assertEquals(FibCalc.calFib(i + 2), FibCalc.calFib(i + 1).add(FibCalc.calFib(i)));
        }
    }
}
