package conversions;

import org.junit.Assert;
import org.junit.Test;

import static conversions.DecimalToOctal.toOctal;

public class DecimalToOctalTest {

    @Test
    public void test() {
        int n = 12345678;
        Assert.assertEquals(57060516, toOctal(n));
    }
}