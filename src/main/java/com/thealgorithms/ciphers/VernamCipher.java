package com.thealgorithms.ciphers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class VernamCipher {
	private final static String NAME = "Vernam Cipher";
	private final static char encryptionArr[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	public VernamCipher(){

	}

	/*
	 * Returns a String that represents the key of the input length. 
	 * Returns null if there's a problem with the input length.
	 */
	public String generateKey(int length){
		if(length <= 0){
			return null;
		}
		String key = "";
		SecureRandom secureRandom = new SecureRandom();
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//Builds the key.
		for(int i = 0; i < length; i++){
			int randomValue = secureRandom.nextInt(26);
			key += encryptionArr[randomValue];
		}
		return key;
	}

	/*
	 * Encrypts using the formula: key XOR with m
	 * Returns cipher text or null if a error occurred.
	 */
	public String encrypt(String plainText, String key){
		//Make sure the key and the plainText are the same length.
		if(plainText.length() != key.length()){
			return null;
		}
		byte[] plainTextBytes = plainText.getBytes();
		byte[] keyBytes = key.getBytes();
		byte[] encryptedText = new byte[plainTextBytes.length];
		for(int i = 0; i < plainTextBytes.length; i++){
			encryptedText[i] = (byte) (keyBytes[i] ^ plainTextBytes[i]);
		}
		return new String(encryptedText);
	}

	/*
	 * Decrypts using the formula: key XOR ciphertext.
	 * Returns plain text or null if a error occurred. 
	 */
	public String decrypt(String cipherText, String key){
		//Make sure the key and the plainText are the same length.
		if(cipherText.length() != key.length()){
			return null;
		}
		byte[] cipherTextBytes = cipherText.getBytes();
		byte[] keyBytes = key.getBytes();
		byte[] decryptedText = new byte[cipherTextBytes.length];
		for(int i = 0; i < cipherTextBytes.length; i++){
			decryptedText[i] = (byte) (keyBytes[i] ^ cipherTextBytes[i]);
		}
		return new String(decryptedText).toUpperCase();
	}

	/*
	 * Returns the name of the cipher.
	 */
	public String getName(){
		return NAME;
	}

}

// Source code link : https://github.com/jthomasprogrammer/Ciphers/blob/master/src/ciphers/VernamCipher.java