package ciphers;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * This example program shows how AES encryption and decryption with IV can be done in
 * Java. with IV added in password, for same password encrypt different times, the encyption 
 * password is different, this could stop the rainbow attack.
 * @author litaocdl
 */
public class AESCBCWithPadding {


	private static final String ALGORITHM = "AES/CBC/PKCS5Padding" ;
	/**
	 * 1. Generate the key spec, which will be shared for encryption/decryption
	 * 2. Encrypt the clearText for the first time and decrypt it using keySpec
	 * 3. Encrypt the clearText for the second time and decrypt it using keySpec
	 *
	 * 
	 */
	public static void main(String[] args) throws Exception {
		String plainText = "Hello World";
		System.out.println("Original Text:" + plainText);

		//Share the keySpec
		SecretKeySpec keySpec = getSecretKeySpec();
		System.out.println("AES Key with base64 encoded:  " + Base64.getEncoder().encode(keySpec.getEncoded()));


		String cipherText = encryptText(plainText, keySpec);
		String decryptedText = decryptText(cipherText, keySpec);
		System.out.println("First time encrypted Text in Base64:" + cipherText);
		System.out.println("Descrypted Text:" + decryptedText);

		System.out.println("===Encrypt again for same password");

		cipherText = encryptText(plainText, keySpec);
		decryptedText = decryptText(cipherText, keySpec);

		System.out.println("Second time encrypted Text Base64:" + cipherText);
		System.out.println("Descrypted Text:" + decryptedText);

	}

	/**
	 * gets the AES keySpec, the AES we are using here is 256 bit AES which generated random
	 * 
	 * 
	 * @return keySpec 
	 * @throws NoSuchAlgorithmException (from KeyGenrator)
	 * 
	 */
	public static SecretKeySpec getSecretKeySpec() throws NoSuchAlgorithmException {
		KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES");
		aesKeyGenerator.init(256); // The AES key size in number of bits
		SecretKey secKey = aesKeyGenerator.generateKey(); 
		SecretKeySpec keySpec = new SecretKeySpec(secKey.getEncoded(),"AES") ;
		return keySpec;
	}
	/**
	 * Generate a random IV to pad the AES in CBC mode
	 * 
	*/
	public static IvParameterSpec getIvParameterSpec() {
		SecureRandom random = new SecureRandom();
		byte[] iv = new byte[16];
		random.nextBytes(iv);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		return ivSpec;
	}

	/**
	 * Encrypts plainText in AES/CBC using the key and IV, the IV will be append to the password for decryption use
	 * 
	 * @param plainText
	 * @param secKey
	 * @return byteCipherText (The encrypted text)
	 * @throws NoSuchPaddingException             (from Cipher)
	 * @throws NoSuchAlgorithmException           (from Cipher)
	 * @throws InvalidKeyException                (from Cipher)
	 * @throws BadPaddingException                (from Cipher)
	 * @throws IllegalBlockSizeException          (from Cipher)
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String encryptText(String plainText, SecretKeySpec keySpec)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException {

		// AES defaults to AES/ECB/PKCS5Padding 
		Cipher aesCipher = Cipher.getInstance(ALGORITHM);
		IvParameterSpec ivSpec = getIvParameterSpec() ;
		aesCipher.init(Cipher.ENCRYPT_MODE, keySpec,ivSpec);
		byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());

		//append iv to encrypt password
		byte[] result = new byte[byteCipherText.length + 16];
		System.arraycopy(byteCipherText, 0, result, 0, byteCipherText.length);
		System.arraycopy(ivSpec.getIV(), 0, result, byteCipherText.length, 16);
		
		
		byte[] base64Result = Base64.getEncoder().encode(result) ;
		return new String(base64Result);
	}

	/**
	 * Decrypts encrypted String using same  key and IV in the password.
	 * 
	 * @param encrypt String
	 * @param keySpec
	 * @return plainText
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String decryptText(String encryptText, SecretKeySpec keySpec)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException {
		//get decrypted data in byte
		byte[] decode = Base64.getDecoder().decode(encryptText.getBytes()) ;
		// AES defaults to AES/ECB/PKCS5Padding 
		Cipher aesCipher = Cipher.getInstance(ALGORITHM);
		//get the iv byte
		byte[] iv = Arrays.copyOfRange(decode, decode.length-16,decode.length) ;
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		//get the password byte
		byte[] password = Arrays.copyOfRange(decode, 0,decode.length - 16) ;

		aesCipher.init(Cipher.DECRYPT_MODE, keySpec,ivSpec);
		
		byte[] bytePlainText = aesCipher.doFinal(password);
		return new String(bytePlainText);
	}

}
