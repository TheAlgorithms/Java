import Others.GCD;
import org.junit.Test;
import org.junit.Assert;


public class TestGCD {

    @Test
    public void testGCDWithBoundaryValue() {
        int x = 6464500;
        int y = 1546340;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(20, result);

        x = 35;
        y = 41;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);

        //Test with Min Value
        x = Integer.MIN_VALUE;
        y = Integer.MIN_VALUE + 1;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);

        x = Integer.MIN_VALUE + 1;
        y = 500;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);

        x = 198;
        y = Integer.MIN_VALUE;
        result = GCD.gcd(x,y);
        Assert.assertEquals(2, result);

        //Test with Max
        x = Integer.MAX_VALUE;
        y = Integer.MAX_VALUE - 1;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);

        x = Integer.MAX_VALUE;
        y = 1973;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);

        x = 402;
        y = Integer.MAX_VALUE - 1;
        result = GCD.gcd(x,y);
        Assert.assertEquals(6, result);

        //Test with special value

        x = Integer.MAX_VALUE;
        y = Integer.MIN_VALUE;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);

        x = 1;
        y = Integer.MIN_VALUE;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1,result);

        x = 0;
        y = Integer.MAX_VALUE;
        result = GCD.gcd(x,y);
        Assert.assertEquals(2147483647,result);

        x = 1;
        y = Integer.MAX_VALUE;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1,result);
    }

}