import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

/*
This is an Implementation of AES alhgorithm in CBC version 1 mode
Wiki: https://en.wikipedia.org/wiki/Block_cipher_mode_of_operation

@author Ojasva Jain

*/

public class AES_CBC_v1 {
    private static final String FACTORY_INSTANCE = "PBKDF2WithHmacSHA256";
    private static final String CIPHER_INSTANCE = "AES/CBC/PKCS5PADDING";
    private static final String SECRET_KEY_TYPE = "AES";
    private static final byte[] IV_CODE = new byte[16];
    private static final String SECRET_KEY = "yourSecretKey";
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 256;
public static String encrypt(String secretKey, String salt, String value) throws Exception {
        
        Cipher cipher = initCipher(secretKey, salt, Cipher.ENCRYPT_MODE);
        byte[] cipherText = cipher.doFinal(value.getBytes());
        byte[] cipherWithIv = addIVToCipher(cipherText);
        return Base64.getEncoder().encodeToString(cipherWithIv);
    }
public static String decrypt(String secretKey, String salt, String encrypted) throws Exception {
        
        Cipher cipher = initCipher(secretKey, salt, Cipher.DECRYPT_MODE);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        // unpad
        byte[] originalWithoutIv = Arrays.copyOfRange(original, IV_CODE.length, original.length);
        return new String(originalWithoutIv);
    }
private static Cipher initCipher(String secretKey, String salt, int mode) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(FACTORY_INSTANCE);
        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
        SecretKeySpec sKeySpec = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), SECRET_KEY_TYPE);
        Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
        // Generating random IV
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV_CODE);
        cipher.init(mode, sKeySpec, new IvParameterSpec(IV_CODE));
        return cipher;
    }
private static byte[] addIVToCipher(byte[] cipherText) {
        
        byte[] cipherWithIv = new byte[IV_CODE.length + cipherText.length];
        System.arraycopy(IV_CODE, 0, cipherWithIv, 0, IV_CODE.length);
        System.arraycopy(cipherText, 0, cipherWithIv, IV_CODE.length, cipherText.length);
        return cipherWithIv;
    }
public static void main(String[] args) throws Exception {
        
        String fSalt = "anySaltYouCanUseOfOn";
        String plainText = "M0993000353";
        String cipherText = encrypt(SECRET_KEY, fSalt, plainText);
        System.out.println("Cipher: " + cipherText);
        String decryptedText = decrypt(SECRET_KEY, fSalt, cipherText);
        System.out.println("Decrypted: " + decryptedText);
    }
}