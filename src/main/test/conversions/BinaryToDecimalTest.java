package conversions;

import org.junit.Assert;
import org.junit.Test;

import static conversions.BinaryToDecimal.toDecimal;

public class BinaryToDecimalTest {

    @Test
    public void test() {
        int n = 101010;
        System.out.printf("Binary number: %d%n", n);
        int decimal = toDecimal(n);
        System.out.printf("Decimal equivalent: %s%n", decimal);
        Assert.assertEquals(42, decimal);
    }
}