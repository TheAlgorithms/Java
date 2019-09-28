import Others.GCD;
import org.junit.Test;
import org.junit.Assert;


public class TestGCDAllDUpath {
    // test cho num1
    @Test
    public void testGCD1() {
        int x = 0;
        int y = 150;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(15, result);
    }
    @Test
    public void testGCD2() {
        x = 15;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);
    }
    @Test
    public void testGCD3() {
        x = 15;
        y = 10;
        result = GCD.gcd(x,y);
        Assert.assertEquals(0, result);
    }
    @Test
    public void testGCD4() {
        x = 15;
        y = 20;
        result = GCD.gcd(x,y);
        Assert.assertEquals(0, result);
    }
    // test cho num2
    @Test
    public void testGCD5() {
        x = 40;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(4, result);
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
    // test cho num3
    @Test
    public void testGCD5() {
        x = 0;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(0, result);
    }
    @Test
    public void testGCD6() {
        x = 0;
        y = 40;
        result = GCD.gcd(x,y);
        Assert.assertEquals(4, result);
    }
    @Test
    public void testGCD7() {
        x = 30;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(3, result);
    }
    @Test
    public void testGCD8() {
        x = 30;
        y = 40;
        result = GCD.gcd(x,y);
        Assert.assertEquals(0, result);
    }
