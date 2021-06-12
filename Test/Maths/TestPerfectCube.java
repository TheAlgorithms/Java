package Test.Maths;

import Maths.PerfectCube;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The first 3 tests pass but I found an issue when I start using higher number such as 125 = 5 * 5 * 5.
 * Your method is not efficient because you use Math.pow 1.0/3 => a = 4.9999999999.
 * So it return a = 4 => The method can't determine if 125 is a perfect cube.
 */
@SuppressWarnings("InstantiationOfUtilityClass")
public class TestPerfectCube {
    PerfectCube test = new PerfectCube();

    @Test
    public void testIsPerfectCube() {
        assertTrue(test.isPerfectCube(0));
        assertTrue(test.isPerfectCube(8));
        assertTrue(test.isPerfectCube(27));
        assertTrue(test.isPerfectCube(64));
        assertTrue(test.isPerfectCube(125));
        assertFalse(test.isPerfectCube(9));
        assertFalse(test.isPerfectCube(200));
        assertFalse(test.isPerfectCube(400));
    }
}
