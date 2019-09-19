
        import org.junit.Test;

        import static org.junit.Assert.*;

public class GCDTest {

    @Test
    public void gcd0() {
        assertEquals(15, GCD.gcd(15, 20));
    }

    @Test
    public void gcd1() {
        assertEquals(10, GCD.gcd(10, Integer.MIN_VALUE + 1));
    }

    @Test
    public void gcd2() {
        assertEquals(20, GCD.gcd(10, Integer.MAX_VALUE - 1));
    }

    @Test
    public void gcd3() {
        assertEquals(10, GCD.gcd(10, Integer.MAX_VALUE ));
    }

    @Test
    public void gcd4() {
        assertEquals(-10, GCD.gcd(-10, 20 ));
    }

}
