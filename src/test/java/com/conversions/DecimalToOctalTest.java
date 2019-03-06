package src.test.java.com.conversions;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.conversions.DecimalToOctal;

public class DecimalToOctalTest {
    @Test
    public void testDecimalToOctalTest() {
        DecimalToOctal decimalToOctal = new DecimalToOctal();
        Assert.assertEquals("Incorrect Conversion", "41", decimalToOctal.decimalToOctal("33"));
        Assert.assertEquals("Incorrect Conversion", "5512", decimalToOctal.decimalToOctal("2890"));
        Assert.assertEquals("Incorrect Conversion", "12525252525252525252525241", decimalToOctal.decimalToOctal("50371909150609548946081"));
        Assert.assertEquals("Incorrect Conversion", "13", decimalToOctal.decimalToOctal("11"));
        Assert.assertEquals("Incorrect Conversion", "46703754", decimalToOctal.decimalToOctal("10192876"));
    }
}
