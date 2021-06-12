package Test.Maths;

import Maths.PerfectNumber;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestPerfectNumber {
   PerfectNumber test = new PerfectNumber();

    @Test
    public void testIsPerfectNumber () {
        assertTrue(test.isPerfectNumber(6));     /* 1 + 2 + 3 == 6 */
        assertFalse(test.isPerfectNumber(8));    /* 1 + 2 + 4 != 8 */
        assertTrue(test.isPerfectNumber(28));     /* 1 + 2 + 4 + 7 + 14 == 28 */
        assertFalse(test.isPerfectNumber(30));    /* 1 + 2 + 3 + 5 + 6 + 10 + 15 != 30 */
    }
}
