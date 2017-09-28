package conversions;

import org.junit.Assert;
import org.junit.Test;

import static conversions.DecimalToBinary.convertToBinary;

public class DecimalToBinaryTest {

    @Test
    public void test() {
        int decimal = 100;
        int binary = convertToBinary(decimal);
        Assert.assertEquals(1100100, binary);
    }
}