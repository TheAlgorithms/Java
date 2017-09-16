package conversions;

import org.junit.Assert;
import org.junit.Test;

import static conversions.DecimalToAnyBase.convertToAnyBase;

public class DecimalToAnyBaseTest {

    @Test
    public void test() throws Exception {
        int decInput = 140;
        int base = 2;
        String answer = convertToAnyBase(decInput, base);
        Assert.assertEquals("10001100", answer);
    }
}
