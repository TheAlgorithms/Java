package src.test.java.com.conversions;

import src.main.java.com.conversions.BinaryToGray;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryToGrayTest {

    @Test
    public void testBinaryToGray() {
        BinaryToGray binaryToGray = new BinaryToGray();
        assertEquals("1101", binaryToGray.binaryToGray("1001"));
        assertEquals("11010011101", binaryToGray.binaryToGray("10011101001"));
    }

}
