package Test.Maths;

import Maths.AbsoluteMin;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The same issues with AbsoluteMax.java
 * It should return the absolute value and check condition for empty array.
 */
@SuppressWarnings("InstantiationOfUtilityClass")
public class TestAbsoluteMin {
    int [] testnum1 = {2, -8, 10, -32, 9};
    int [] testnum2 = {3};
    int [] testnum3 = {5, -2, 4};

    AbsoluteMin test = new AbsoluteMin();

    @Test
    public void testAbsMin() {
        assertEquals(2, test.absMin(testnum1));
        assertEquals(3, test.absMin(testnum2));
        assertEquals(2, test.absMin(testnum3));
    }
}
