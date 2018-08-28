package Conversions;

import static org.junit.Assert.*;
import org.junit.Test;

public class DecimalToBinaryTest {
	
	@Test
	public void conventionalConversión_Input2Output10_Right() throws Exception {
		DecimalToBinary db = new DecimalToBinary();
		assertEquals(db.conventionalConversion(2),10);
	}
	
	@Test
	public void conventionalConversión_Input2Output11_Wrong() throws Exception {
		DecimalToBinary db = new DecimalToBinary();
		assertFalse(db.conventionalConversion(2)==11);
	}	

	@Test(expected=Exception.class)
	public void conventionalConversion_NegativeInput_Exception() throws Exception {
		DecimalToBinary db = new DecimalToBinary();
		db.conventionalConversion(-2);
	}
	
	@Test
	public void conventionalConversion_InputZero_OutputZero() throws Exception {
		DecimalToBinary db = new DecimalToBinary();
		assertEquals(db.conventionalConversion(0),0);
	}
	
	@Test
	public void conventionalConversion_BigInput_BigOutput() throws Exception {
		DecimalToBinary db = new DecimalToBinary();
		assertEquals(db.conventionalConversion(345),101011001);
	} 
	
	@Test
	public void bitwiseConversion_Input2Output10_Right() throws Exception {
		DecimalToBinary db = new DecimalToBinary();
		assertEquals(db.bitwiseConversion(2),10);
	}
	
	@Test
	public void bitwiseConversión_Input2Output11_Wrong() throws Exception {
		DecimalToBinary db = new DecimalToBinary();
		assertFalse(db.bitwiseConversion(2)==11);
	}
	

	@Test(expected=Exception.class)
	public void bitwiseConversion_NegativeInput_Exception() throws Exception {
		DecimalToBinary db = new DecimalToBinary();
		db.bitwiseConversion(-2);
	}
	
	@Test
	public void bitwiseConversion_InputZero_OutputZero() throws Exception {
		DecimalToBinary db = new DecimalToBinary();
		assertEquals(db.bitwiseConversion(0),0);
	}
	
	@Test
	public void bitwiseConversion_BigInput_BigOutput() throws Exception {
		DecimalToBinary db = new DecimalToBinary();
		assertEquals(db.bitwiseConversion(345),101011001);
	} 
	
}
