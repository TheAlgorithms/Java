// write test junit cases for fibonancy.java

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class fibonancy {

    public class fibonancyTest {
        private fibonancy fib;
        @Before
        public void setUp() {
            fib = new fibonancy();
        }
        @Test
        public void testFibonancy() {
            assertEquals(fib.fibonancy(0), 0);
            assertEquals(fib.fibonancy(1), 1);
            assertEquals(fib.fibonancy(2), 1);
            assertEquals(fib.fibonancy(3), 2);
            assertEquals(fib.fibonancy(4), 3);
            assertEquals(fib.fibonancy(5), 5);
            assertEquals(fib.fibonancy(6), 8);
            assertEquals(fib.fibonancy(7), 13);
            assertEquals(fib.fibonancy(8), 21);
            assertEquals(fib.fibonancy(9), 34);
            assertEquals(fib.fibonancy(10), 55);
            assertEquals(fib.fibonancy(11), 89);
            assertEquals(fib.fibonancy(12), 144);
            assertEquals(fib.fibonancy(13), 233);
            assertEquals(fib.fibonancy(14), 377);
            assertEquals(fib.fibonancy(15), 610);
            assertEquals(fib.fibonancy(16), 987);
            assertEquals(fib.fibonancy(17), 1597);
            assertEquals(fib.fibonancy(18), 2584);
            assertEquals(fib.fibonancy(19), 4181);
            assertEquals(fib.fibonancy(20), 6765);
            assertEquals(fib.fibonancy(21), 10946);
            assertEquals(fib.fibonancy(22), 17711);
            assertEquals(fib.fibonancy(23), 28657);
}