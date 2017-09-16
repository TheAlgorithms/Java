package conversions;

import org.junit.Assert;
import org.junit.Test;

import static conversions.AnyBaseToDecimal.convertToDecimal;

public class AnyBaseToDecimalTest {

    @Test
    public void test() throws Exception {
        String inp = "123123";
        int base = 8;
        String answer = convertToDecimal(inp, base);
        Assert.assertEquals("42579", answer);
    }

    @Test
    public void testFail() throws Exception {
        String inp = "128";
        int base = 8;
        String answer = convertToDecimal(inp, base);
        Assert.assertEquals("Invalid Number", answer);
    }
}