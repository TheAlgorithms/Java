package ciphers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 * This example program shows how AES encryption and decryption can be done in
 * Java. Please note that secret key and encrypted text is unreadable binary and
 * hence in the following program we display it in hexadecimal format of the
 * underlying bytes.
 * This program is an extension of AESEncryption 
 * with GCM (https://en.wikipedia.org/wiki/Galois/Counter_Mode).
 *
 */
public class AESGCMEncryption {

	private static final String AES_CHIPER_SECURE_ALGORITHM = "AES/GCM/NoPadding";
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	/**
	 * 1. Generate a plain text for encryption 2. Get a secret key (printed in
	 * hexadecimal form). In actual use this must by encrypted and kept safe. The
	 * same key is required for decryption.
	 * 
	 */
	public static void main(String[] args) throws Exception {
		String plainText = "Hello World";
		SecretKey secKey = getSecretEncryptionKey();
		byte[] cipherText = encryptText(plainText, secKey);
		String decryptedText = decryptText(cipherText, secKey);

		System.out.println("Original Text:" + plainText);
		System.out.println("AES/GCM/NoPadding Key (Hex Form):" + bytesToHex(secKey.getEncoded()));
		System.out.println("Encrypted Text (Hex Form):" + bytesToHex(cipherText));
		System.out.println("Descrypted Text:" + decryptedText);
	}

	/**
	 * gets the AES encryption key. In your actual programs, this should be safely
	 * stored.
	 * 
	 * @return secKey (Secret key that we encrypt using it)
	 * @throws NoSuchAlgorithmException (from KeyGenrator)
	 * 
	 */
	public static SecretKey getSecretEncryptionKey() throws NoSuchAlgorithmException {
		KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES");
		aesKeyGenerator.init(128); // The AES key size in number of bits
		return aesKeyGenerator.generateKey();
	}

	/**
	 * Encrypts plainText in AES/GCM/NoPadding using the secret key
	 * 
	 * @param plainText
	 * @param secKey
	 * @return byteCipherText                     (The encrypted text)
	 * @throws NoSuchPaddingException             (from Cipher)
	 * @throws NoSuchAlgorithmException           (from Cipher)
	 * @throws InvalidKeyException                (from Cipher)
	 * @throws BadPaddingException                (from Cipher)
	 * @throws IllegalBlockSizeException          (from Cipher)
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] encryptText(String plainText, SecretKey secKey)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		
		byte[] bytesPlainText = plainText.getBytes(StandardCharsets.UTF_8);
		Cipher aesCipher = Cipher.getInstance(AES_CHIPER_SECURE_ALGORITHM);
		aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
		byte[] encryptedText = aesCipher.doFinal(plainText.getBytes());
		byte[] iv = aesCipher.getIV(); 
		byte[] finalEncryptedText = new byte[12 + bytesPlainText.length + 16];
		
		System.arraycopy(iv, 0, finalEncryptedText, 0, 12);
		System.arraycopy(encryptedText, 0, finalEncryptedText, 12, encryptedText.length);
		return finalEncryptedText;
	}

	/**
	 * Decrypts encrypted byte array using the key used for encryption.
	 * 
	 * @param byteCipherText
	 * @param secKey
	 * @return plainText
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidAlgorithmParameterException
	 * @throws UnsupportedEncodingException
	 */
	public static String decryptText(byte[] byteCipherText, SecretKey secKey)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
		
		Cipher aesCipher = Cipher.getInstance(AES_CHIPER_SECURE_ALGORITHM);
		GCMParameterSpec gcmParams = new GCMParameterSpec(128, byteCipherText, 0, 12);
		aesCipher.init(Cipher.DECRYPT_MODE, secKey, gcmParams);
		byte[] decryptedText = aesCipher.doFinal(byteCipherText, 12, byteCipherText.length - 12);
		return new String(decryptedText, StandardCharsets.UTF_8);
	}

	/**
	 * Convert a binary byte array into readable hex form
	 * Credit
	 * {@link 
	 * https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java/9855338#9855338}
	 * @param hash
	 *            (in binary)
	 * @return hexHash
	 */
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
