package ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Nguyen Duy Tiep on 23-Oct-17.
 */
public class RSA {
    private BigInteger modulus, privateKey, publicKey;

    public RSA(int bits) {
        generateKeys(bits);
    }

    public synchronized String encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(publicKey, modulus).toString();
    }

    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    public synchronized String decrypt(String message) {
        return new String((new BigInteger(message)).modPow(privateKey, modulus).toByteArray());
    }

    public synchronized BigInteger decrypt(BigInteger message) {
        return message.modPow(privateKey, modulus);
    }

    /** Generate a new public and private key set. */
    public synchronized void generateKeys(int bits) {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bits / 2, 100, r);
        BigInteger q = new BigInteger(bits / 2, 100, r);
        modulus = p.multiply(q);

        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        publicKey = new BigInteger("3");

        while (m.gcd(publicKey).intValue() > 1) {
            publicKey = publicKey.add(new BigInteger("2"));
        }

        privateKey = publicKey.modInverse(m);
    }

    /** Trivial test program. */
    public static void main(String[] args) {
        RSA rsa = new RSA(1024);

        String text1 = "This is a message";
        System.out.println("Plaintext: " + text1);

        String ciphertext = rsa.encrypt(text1);
        System.out.println("Ciphertext: " + ciphertext);

        System.out.println("Plaintext: " + rsa.decrypt(ciphertext));
    }
}
