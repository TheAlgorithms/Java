import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FactorialTest {

    @Test
    public void testFactorialOfOne() {
        assertEquals(1, Factorial.factorial(1));
    }

    @Test
    public void testFactorialOfPositiveNumbers() {
        assertEquals(120, Factorial.factorial(5));
        assertEquals(720, Factorial.factorial(6));
        assertEquals(5040, Factorial.factorial(7));
    }

    @Test
    public void testFactorialOfTen() {
        assertEquals(3628800, Factorial.factorial(10));
    }

    @Test
    public void testNegativeNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(-1));
    }
}
