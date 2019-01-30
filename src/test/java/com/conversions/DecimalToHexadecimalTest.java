package src.test.java.com.conversions;

import org.junit.Assert;
import org.junit.Test;

public class DecimalToHexadecimalTest {

    @Test
    public void testDecimalToHexadecimalTest() {
        DecimalToHexadecimal decimalToHexadecimal = new DecimalToHexadecimal();
        Assert.assertEquals("Incorrect Conversion", "F", decimalToHexadecimal.decimalToHex("15"));
        Assert.assertEquals("Incorrect Conversion", "121", decimalToHexadecimal.decimalToHex("289"));
        Assert.assertEquals("Incorrect Conversion", "AAAAAAAAAAAAAAAAAA1", decimalToHexadecimal.decimalToHex("50371909150609548946081"));
        Assert.assertEquals("Incorrect Conversion", "A", decimalToHexadecimal.decimalToHex("10"));
        Assert.assertEquals("Incorrect Conversion", "8B2F", decimalToHexadecimal.decimalToHex("35631"));
    }
}