package Test.Maths;

import Maths.PythagoreanTriple;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestPythagoreanTriple {
    // Test 1
    int a1 = 3;
    int b1 = 4;
    int c1 = 5;
    // Test 2
    int a2 = 10;
    int b2 = 20;
    int c2 = 25;
    // Test 3
    int a3 = 6;
    int b3 = 8;
    int c3 = 10;
    // Test 4
    int a4 = -6;
    int b4 = 8;
    int c4 = -10;

    PythagoreanTriple test = new PythagoreanTriple();

    @Test
    public void testIsPythagTriple() {
        assertTrue(test.isPythagTriple(a1, b1, c1));
        assertFalse(test.isPythagTriple(a2, b2, c2));
        assertTrue(test.isPythagTriple(a3, b3, c3));
        assertFalse(test.isPythagTriple(a4, b4, c4));
    }
}
