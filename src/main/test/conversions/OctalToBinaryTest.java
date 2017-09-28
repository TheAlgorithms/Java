package conversions;

import org.junit.Assert;
import org.junit.Test;

import static conversions.OctalToBinary.convertOctalToBinary;

public class OctalToBinaryTest {

    @Test
    public void test() {
        int number = 500;
        int answer = convertOctalToBinary(number);
        Assert.assertEquals(320, answer);
    }
}