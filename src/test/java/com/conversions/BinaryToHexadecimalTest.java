package src.test.java.com.conversions;

import org.junit.Test;
import src.main.java.com.conversions.BinaryToHexadecimal;
import org.junit.Assert;

public class BinaryToHexadecimalTest {

    @Test
    public void testBinaryToHexadecimal(){
        BinaryToHexadecimal binaryToHexadecimal = new BinaryToHexadecimal();
        Assert.assertEquals("Incorrect Conversion", "2A", binaryToHexadecimal.binToHex(101010));
        Assert.assertEquals("Incorrect Conversion", "24", binaryToHexadecimal.binToHex(100100));
    }
}