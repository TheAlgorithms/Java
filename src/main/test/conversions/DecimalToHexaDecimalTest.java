package conversions;

import org.junit.Assert;
import org.junit.Test;

import static conversions.DecimalToHexaDecimal.decToHex;

public class DecimalToHexaDecimalTest {

    @Test
    public void test() {
        int dec = 305445566;
        String hexFromJdk = Integer.toHexString(dec);
        String hexFromOurClass = decToHex(dec);
        Assert.assertEquals(hexFromJdk.toUpperCase(), hexFromOurClass);
    }
}