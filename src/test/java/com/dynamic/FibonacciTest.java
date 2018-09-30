package src.test.java.com.dynamic;

import src.main.java.com.dynamic.Fibonacci;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FibonacciTest {

    @Test
    public void testFibonacci() {
        Fibonacci fibonacci = new Fibonacci();
        assertEquals(0, fibonacci.fibonacci(0));
        assertEquals(1, fibonacci.fibonacci(1));
        assertEquals(34, fibonacci.fibonacci(9));
        assertEquals(144, fibonacci.fibonacci(12));
    }

}
