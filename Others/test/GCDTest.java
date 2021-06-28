import org.junit.Test;

import static org.junit.Assert.*;

public class GCDTest {

    @Test
    public void gcd() {
        assertEquals(10, GCD.gcd(10, 20));
    }

    @Test
    public void gcd1() {
        assertEquals(1, GCD.gcd(10, Integer.MIN_VALUE + 1));
    }

    @Test
    public void gcd2() {
        assertEquals(2, GCD.gcd(10, Integer.MAX_VALUE - 1));
    }

    @Test
    public void gcd3() {
        assertEquals(1, GCD.gcd(10, Integer.MAX_VALUE ));
    }

    @Test
    public void gcd4() {
        assertEquals(10, GCD.gcd(-10, 20 ));
    }

/*
    @Test
    public void gcd5() {
        assertEquals(1, GCD.gcd(10, (Math.pow(2, 31)) ));
    }

    @Test
    public void gcd6() {
        assertEquals(null, GCD.gcd(10, ((int)Math.pow(2, 31))*(-1) - 1 ));
    }

    @Test
    public void gcd7() {
        assertEquals(null, GCD.gcd(10, Integer.MIN_VALUE));
    }
*/

}