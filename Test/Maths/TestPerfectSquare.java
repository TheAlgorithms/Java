package Test.Maths;

import Maths.PerfectSquare;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestPerfectSquare {
    PerfectSquare test = new PerfectSquare();

    @Test
    public void testIsPerfectSquare() {
        assertFalse(test.isPerfectSquare(-5));
        assertFalse(test.isPerfectSquare(2));
        assertTrue(test.isPerfectSquare(4));
        assertTrue(test.isPerfectSquare(9));
        assertTrue(test.isPerfectSquare(0));
    }
}
