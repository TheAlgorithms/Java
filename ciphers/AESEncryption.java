package ciphers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 * This example program shows how AES encryption and decryption can be done in
 * Java. Please note that secret key and encrypted text is unreadable binary and
 * hence in the following program we display it in hexadecimal format of the
 * underlying bytes.
 * 
 */
public class AESEncryption {

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
		System.out.println("AES Key (Hex Form):" + bytesToHex(secKey.getEncoded()));
		System.out.println("Encrypted Text (Hex Form):" + bytesToHex(cipherText));
		System.out.println("Descrypted Text:" + decryptedText);

	}

	/**
	 * gets the AES encryption key. In your actual programs, this should be safely
	 * stored.
	 * 
	 * @return secKey (Secret key that we encrypt using it)
	 * @throws NoSuchAlgorithmException
	 *             (from KeyGenrator)
	 * 
	 */
	public static SecretKey getSecretEncryptionKey() throws NoSuchAlgorithmException {
		KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES");
		aesKeyGenerator.init(128); // The AES key size in number of bits
		SecretKey secKey = aesKeyGenerator.generateKey();
		return secKey;
	}

	/**
	 * Encrypts plainText in AES using the secret key
	 * 
	 * @param plainText
	 * @param secKey
	 * @return byteCipherText (The encrypted text)
	 * @throws NoSuchPaddingException
	 *             (from Cipher)
	 * @throws NoSuchAlgorithmException
	 *             (from Cipher)
	 * @throws InvalidKeyException
	 *             (from Cipher)
	 * @throws BadPaddingException
	 *             (from Cipher)
	 * @throws IllegalBlockSizeException
	 *             (from Cipher)
	 */
	public static byte[] encryptText(String plainText, SecretKey secKey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
		byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
		return byteCipherText;
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
	 */
	public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.DECRYPT_MODE, secKey);
		byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
		return new String(bytePlainText);
	}

	/**
	 * Convert a binary byte array into readable hex form
	 * 
	 * @param hash
	 *            (in binary)
	 * @return hexHash
	 */
	private static String bytesToHex(byte[] hash) {
		return DatatypeConverter.printHexBinary(hash);
	}
}
