package Others;

import org.junit.Test;
import Others.GCD;
import static org.junit.Assert.*;

public class TestGCD {
	@Test
    public void escapeTestCase0() throws Exception {
        int[] input = {10};
        int ouptut;
        int expected = 10;
        output = GCD.gcd(input);

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase1() throws Exception {
        int[] input = {1,0};
        int ouptut;
        int expected = 1;
        output = GCD.gcd(input);

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase2() throws Exception {
        int[] input = {16,4};
        int ouptut;
        int expected = 4;
        output = GCD.gcd(input);

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase3() throws Exception {
        int[] input = {4,16};
        int ouptut;
        int expected = 4;
        output = GCD.gcd(input);

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase4() throws Exception {
        int[] input = {9,6,3};
        int ouptut;
        int expected = 3;
        output = GCD.gcd(input);

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase5() throws Exception {
        int[] input = {5,3};
        int ouptut;
        int expected = 1;
        output = GCD.gcd(input);

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase6() throws Exception {
        int[] input = {3,5};
        int ouptut;
        int expected = 1;
        output = GCD.gcd(input);

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase7() throws Exception {
        int[] input = {0,6};
        int ouptut;
        int expected = 6;
        output = GCD.gcd(input);

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase8() throws Exception {
        int[] input = {5,4};
        int ouptut;
        int expected = 1;
        output = GCD.gcd(input);

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase9() throws Exception {
        int[] input = {2,6};
        int ouptut;
        int expected = 2;
        output = GCD.gcd(input);

        assertEquals(output,expected);
    }
}