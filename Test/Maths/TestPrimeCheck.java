package Test.Maths;

import Maths.PrimeCheck;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestPrimeCheck {
    int testnum1 = 2;
    int testnum2 = 1;
    int testnum3 = 0;
    int testnum4 = 4;
    PrimeCheck test = new PrimeCheck();

    @Test
    public void testPrimeCheck() {
        assertTrue(test.isPrime(testnum1));
        assertFalse(test.isPrime(testnum2));
        assertFalse(test.isPrime(testnum3));
        assertFalse(test.isPrime(testnum4));
    }
}
