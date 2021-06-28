import Others.GCD;
import org.junit.Test;
import org.junit.Assert;


public class TestGCD {

    @Test
    public void testGCDWithBoundaryValue() {
        int x = 0;
        int y = 150;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(150, result);

        x = 15;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(15, result);

        x = 0;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(0, result);

        x = 50;
        y = 60;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);

        x = 40;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(40, result);

        x = 50;
        y = 40;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);

        x = 30;
        y = 0;
        result = GCD.gcd(x,y);
        Assert.assertEquals(30, result);

        x = 30;
        y = 40;
        result = GCD.gcd(x,y);
        Assert.assertEquals(1, result);
    }

}