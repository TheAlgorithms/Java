package Test.Maths;

import Maths.AbsoluteMax;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * I create the 3 test cases:
 * Test 1: 5 random positive and negative numbers.
 * Test 2: length of 1.
 * Test 3: 3 random positive numbers.
 *
 * So I have found a few issues:
 * It doesn't return the positive number or absolute max that I expected it would be.
 * And you should check condition for array not empty.
 */

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestAbsoluteMax {
    int [] testnum1 = {2, -8, 10, -32, 9};
    int [] testnum2 = {2};
    int [] testnum3 = {5, 2, 4};

    AbsoluteMax test = new AbsoluteMax();

    @Test
    public void testAbsMax() {
        assertEquals(32, AbsoluteMax.absMax(testnum1));
        assertEquals(2, AbsoluteMax.absMax(testnum2));
        assertEquals(5, AbsoluteMax.absMax(testnum3));
    }
}
