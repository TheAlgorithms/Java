package test.com.java.conversions;

import main.com.java.conversions.DecimalToAnyBase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DecimalToAnyBaseTest {

    @Test
    public void testDecimalToAnyBase() {
        DecimalToAnyBase decimalToAnyBase = new DecimalToAnyBase();
        assertEquals("Incorrect Conversion", "100", decimalToAnyBase.convertToAnyBase(4, 2));
        assertEquals("Incorrect Conversion", "11", decimalToAnyBase.convertToAnyBase(4, 3));
    }
}
