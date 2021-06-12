package Test.Maths;

import Maths.Pow;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * I see that your algorithms do not support negative exponents.
 * And you should also check condition for the base to be different from 0 when the exponent is negative.
 */
@SuppressWarnings("InstantiationOfUtilityClass")
public class TestPow {
    // Test 1: 2 positive integers
    int a1 = 2;
    int b1 = 3;
    // Test 2: exponent = 0
    int a2 = 6;
    int b2 = 0;
    // Test 3: base = 0
    int a3 = 0;
    int b3 = 8;
    // Test 4: negative exponent
    int a4 = 4;
    int b4 = -3;
    // Test 5: negative base
    int a5 = -3;
    int b5 = 3;

    Pow test = new Pow();

    @SuppressWarnings("deprecation")
    @Test
    public void testPow() {
        assertEquals(8, test.pow(a1, b1), 0.000001);
        assertEquals(1, test.pow(a2, b2), 0.000001);
        assertEquals(0, test.pow(a3, b3), 0.000001);
        assertEquals(Math.pow(4, -3), test.pow(a4, b4), 0.000001);
        assertEquals(-27, test.pow(a5, b5), 0.000001);
    }
}
