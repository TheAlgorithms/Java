import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.ValueSource;

public class FibonacciNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    public void testNthFibonacci(int input) {
        int result = FibonacciNumber.nthFibonacci(input);
        switch (input) {
            case 0:
                assertEquals(0, result);
                break;
            case 1:
                assertEquals(1, result);
                break;
            case 2:
                assertEquals(1, result);
                break;
            case 5:
                assertEquals(5, result);
                break;
            case 10:
                assertEquals(55, result);
                break;
            default:
                fail("Unexpected input value: " + input);
        }
    }
}
