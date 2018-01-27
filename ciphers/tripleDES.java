/** Triple DES is symmetric key block cipher which uses the DES cipher algorithm three times to 
  encrypt and decrypt code
 */


import javax.crypto.spec.*;
import java.security.*;
import javax.crypto.*;
  
public class tripleDES
{
   public static void main(String []args) throws Exception {
      String toEncrypt = "I am the king of the world";
  
      System.out.println("original text: " + toEncrypt);
      byte[] encrypted = encrypt(toEncrypt, "password");
      System.out.println("Encrypted text: " + encrypted);
      
      String decrypted = decrypt(encrypted, "password");
     
      System.out.println("Decrypted text: " + decrypted);
   }
  
   public static byte[] encrypt(String toEncrypt, String key) throws Exception {
      // create a binary key from the argument key (seed)
      SecureRandom sr = new SecureRandom(key.getBytes());
      KeyGenerator kg = KeyGenerator.getInstance("DESede");
      kg.init(sr);
      SecretKey sk = kg.generateKey();
  
      // create an instance of cipher
      Cipher cipher = Cipher.getInstance("DESede");
  
      // initialize the cipher with the key
      cipher.init(Cipher.ENCRYPT_MODE, sk);
  
      // enctypt!
      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
  
      return encrypted;
   }
  
   public static String decrypt(byte[] toDecrypt, String key) throws Exception {
      // create a binary key from the argument key (seed)
      SecureRandom sr = new SecureRandom(key.getBytes());
      KeyGenerator kg = KeyGenerator.getInstance("DESede");
      kg.init(sr);
      SecretKey sk = kg.generateKey();
  
      // do the decryption with that key
      Cipher cipher = Cipher.getInstance("DESede");
      cipher.init(Cipher.DECRYPT_MODE, sk);
      byte[] decrypted = cipher.doFinal(toDecrypt);
  
      return new String(decrypted);
   }
}
