package ciphers;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class VigenereTest {
	private static Vigenere Vig;
	
	@BeforeClass
	
	public static void prepareForalltest() {
		Vig = new Vigenere();
	}
	@Test
	public void testencrypt01() {
		assertTrue(Vig.encrypt("Hello World!", "itsakey").equals("Pxdly Uwkdd!"));			
	}
	
	@Test
	public void testencrypt02() {
		assertTrue(Vig.encrypt("Hello World!", "123445").equals("8PXY\\ GZ^YQ!"));
	}
	
	
	@Test
	public void testencrypt03() {
		assertTrue(Vig.encrypt("Hello World!", "123445984126512635412366541157474313545487413242311346542131367975431334343246742313467421364697241346576").equals("8PXY\\ O`_VO!"));
	}
	
	@Test
	public void testencrypt04() {
		assertTrue(Vig.encrypt("Hello World!", "hihhi%^&(%$#&&*(").equals("Omssw ZNSJ[!"));
	}
	
	@Test
	public void testencrypt05() {
		assertTrue(Vig.encrypt("h", "hello").equals("o"));
	}
	
	
}
