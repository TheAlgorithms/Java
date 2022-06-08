import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class FibonacciTest {
    private Fibonacci fibonacci;

    @Before
    public void setUp() {
        fibonacci = new Fibonacci();
    }
    
    public class Fibonacci {
        public int calculate(int index) {
            return 1;
        }
    }    
    
    @Test
    public void calculate_0() {
        int testIndex = 0;
        int expectedResult = 1;

        assertEquals(expectedResult, fibonacci.calculate(testIndex));
    }
    
    @Test
    public void calculate_4() {
        int testIndex = 4;
        int expectedResult = 5;

        assertEquals(expectedResult, fibonacci.calculate(testIndex));
    }
    
    @Test
    public void calculate_5() {
        int testIndex = 5;
        int expectedResult = 8;

        assertEquals(expectedResult, fibonacci.calculate(testIndex));
    }
    
    public class Fibonacci1 {
        public int calculate(int index) {
            if (index == 0 || index == 1) {
                return 1;
            }

            return calculate(index - 1) + calculate(index - 2);
        }
    }
    
}    
