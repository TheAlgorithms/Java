import org.junit.Test;

import static org.junit.Assert.*;

public class GCDTest {
    /*
    * Data flow testing
    */

    /*
    * Case 1: number1 is 0 and number2 is not 0
    */
    @Test
    public void testGCD1() {
        int num1 = 0;
        int num2 = 10;
        assertEquals(10, GCD.gcd(num1, num2));
    }

    /*
     * Case 2: number1 is not 0 and number2 is 0
     */
    @Test
    public void testGCD2() {
        int num1 = 0;
        int num2 = 10;
        assertEquals(num2, GCD.gcd(num1, num2));
    }

    /*
     * Case 3: num1 and num2 are not 0 && num1 > num2
     */
    @Test
    public void testGCD3() {
        int num1 = 9;
        int num2 = 3;
        assertEquals(3, GCD.gcd(num1, num2));
    }

    /*
     * Case 4: num1 and num2 are not 0 && num1 < num2
     */
    @Test
    public void testGCD4() {
        int num1 = 35;
        int num2 = 50;
        assertEquals(5, GCD.gcd(num1, num2));
    }
}