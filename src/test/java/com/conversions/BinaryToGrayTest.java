package src.test.java.com.conversions;

import src.main.java.com.conversions.BinaryToGray;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BinaryToGrayTest
{
	
	@Test
	public void testBinaryToGray()
	{
		BinaryToGray btog = new BinaryToGray();
		assertEquals("1101", btog.binaryToGray("1001"));
		assertEquals("11010011101",btog.binaryToGray("10011101001"));
	}
	
}
