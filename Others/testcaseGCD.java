import Others.GCD;
import org.junit.Test;
import org.junit.Assert;


public class TestGCD {
    @Test
    public void testGCD1() {
        int x = 0;
        int y = 150;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(150, result);
    }
    @Test
    public void testGCD2() {
        x = 15;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(15, result);
    }
    @Test
    public void testGCD3() {
        x = 0;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(0, result);
    }
    @Test
    public void testGCD4() {
        x = 50;
        y = 60;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);
    }
    @Test
    public void testGCD5() {
        x = 40;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(40, result);
    }
    @Test
    public void testGCD6() {
        x = 50;
        y = 40;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);
    }
    @Test
    public void testGCD7() {
        x = 30;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(30, result);
    }
    @Test
    public void testGCD8() {
        x = 30;
        y = 40;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);
    }

}