/**
Author : SUBHAM SANGHAI
A Java implementation of RSA Cipher.
/RSA is an algorithm used by modern computers to encrypt and decrypt messages. It is an asymmetric cryptographic algorithm. Asymmetric means that there are two different keys /
**/

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA 
{
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   private BigInteger privateKey;
   private BigInteger publicKey;
   private BigInteger modulus;

   // generate an N-bit (roughly) public and private key
   RSA(int N) 
   {
      	BigInteger p = BigInteger.probablePrime(N/2, random);
      	BigInteger q = BigInteger.probablePrime(N/2, random);
      	BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

      	modulus    = p.multiply(q);                                  
      	publicKey  = new BigInteger("65537");     // common value in practice = 2^16 + 1
      	privateKey = publicKey.modInverse(phi);
   }


   BigInteger encrypt(BigInteger message) 
   {
      return message.modPow(publicKey, modulus);
   }

   BigInteger decrypt(BigInteger encrypted) 
   {
      return encrypted.modPow(privateKey, modulus);
   }

   public String toString() 
   {
      String s = "";
      s += "public  = " + publicKey  + "\n";
      s += "private = " + privateKey + "\n";
      s += "modulus = " + modulus;
      return s;
   }
 
   public static void main(String[] args) 
   {
      Scanner input = new Scanner(System.in);
	  System.out.println("Enter the key generater");
      int N = input.nextInt();
      RSA key = new RSA(N);
      // Print the generated keys
      System.out.println(key);

      // Take string message as input
      String s = input.nextLine();
      byte[] bytes = s.getBytes();
      BigInteger message = new BigInteger(bytes);

      BigInteger encrypt = key.encrypt(message);
      BigInteger decrypt = key.decrypt(encrypt);
      System.out.println("message   = " + message);
      System.out.println("encrypted = " + encrypt);
      System.out.println("decrypted = " + decrypt);
   }
}



