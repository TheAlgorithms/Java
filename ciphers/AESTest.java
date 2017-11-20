package ciphers;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class AESTest {

	@Test
	public void testSubBytes() {
		BigInteger x = new BigInteger("00ff01ff02ff03ff04ff05ff06ff07ff",16);
		BigInteger expected = new BigInteger("63167c1677167b16f2166b166f16c516",16);
		
		assertEquals(expected,AES.subBytes(x));
	}

	@Test
	public void testSubBytesDec() {
		BigInteger x = new BigInteger("63167c1677167b16f2166b166f16c516",16);
		BigInteger expected = new BigInteger("00ff01ff02ff03ff04ff05ff06ff07ff",16);
		
		assertEquals(expected,AES.subBytesDec(x));
	}

	@Test
	public void testShiftRows() {
		BigInteger x = new BigInteger("00102030011121310212223203132333",16);
		BigInteger expected = new BigInteger("00112233011223300213203103102132",16);
		
		assertEquals(expected,AES.shiftRows(x));
	}

	@Test
	public void testShiftRowsDec() {
		BigInteger x = new BigInteger("00112233011223300213203103102132",16);
		BigInteger expected = new BigInteger("00102030011121310212223203132333",16);
		
		assertEquals(expected,AES.shiftRowsDec(x));
	}

	@Test
	public void testMixColumns() {
		BigInteger x = new BigInteger("db135345f20a225c010101012d26314c",16);
		BigInteger expected = new BigInteger("8e4da1bc9fdc589d010101014d7ebdf8",16);
		
		assertEquals(expected,AES.mixColumns(x));
	}

	@Test
	public void testMixColumnsDec() {
		BigInteger x = new BigInteger("8e4da1bc9fdc589d010101014d7ebdf8",16);
		BigInteger expected = new BigInteger("db135345f20a225c010101012d26314c",16);
		
		assertEquals(expected,AES.mixColumnsDec(x));
	}

	@Test
	public void testEncryptAndDecryptCorrectKey() {
		// Plaintext/Ciphertext pairing with correct key
		BigInteger key = new BigInteger("f0f1f2f3f4f5f6f708090a0b0c0d0e0f", 16);
		BigInteger plaintext = new BigInteger("e9870a476d8d0bc43abde33cba26747e", 16);
		BigInteger ciphertext = new BigInteger("adcfc0ed15292419cb796167bc02b669", 16);
		
		BigInteger encryptedPlaintext = AES.encrypt(plaintext,key);
		BigInteger decryptedCiphertext = AES.decrypt(ciphertext,key);
				
		assertEquals(plaintext,decryptedCiphertext);
		assertEquals(ciphertext,encryptedPlaintext);
	}

	@Test
	public void testEncryptAndDecryptWrongKey() {
		// Plaintext/Ciphertext Pairing with incorrect key
		BigInteger key = new BigInteger("f4f1f2f3f4f5f6f708090a0b0c0d0e0f", 16);
		BigInteger plaintext = new BigInteger("e9870a476d8d0bc43abde33cba26747e", 16);
		BigInteger ciphertext = new BigInteger("adcfc0ed15292419cb796167bc02b669", 16);
		
		BigInteger encryptedPlaintext = AES.encrypt(plaintext,key);
		BigInteger decryptedCiphertext = AES.decrypt(plaintext,key);
		
		assertNotEquals(plaintext,decryptedCiphertext);
		assertNotEquals(ciphertext,encryptedPlaintext);
	}

}
