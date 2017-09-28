package conversions;

import org.junit.Assert;
import org.junit.Test;

import static conversions.OctalToDecimal.convertOctalToDecimal;

public class OctalToDecimalTest {

    @Test
    public void test() {
        int someNumber = 10001;
        int answer = convertOctalToDecimal(someNumber);
        System.out.println("Decimal equivalent: " + answer);
        Assert.assertEquals(4097, answer);
    }
}