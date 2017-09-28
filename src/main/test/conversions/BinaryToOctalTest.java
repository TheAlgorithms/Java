package conversions;

import org.junit.Assert;
import org.junit.Test;

import static conversions.BinaryToOctal.convertBinaryToOctal;

public class BinaryToOctalTest {

    @Test
    public void test() {
        int someNumber = 12312;
        int octal = convertBinaryToOctal(someNumber);
        Assert.assertEquals(48, octal);
    }
}