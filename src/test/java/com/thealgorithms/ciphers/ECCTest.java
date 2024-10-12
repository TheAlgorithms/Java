package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

/**
 * ECCTest - Unit tests for the ECC (Elliptic Curve Cryptography) implementation.
 * This class contains various test cases to validate the encryption and decryption functionalities.
 * It ensures the correctness and randomness of ECC operations.
 *
 * @author xuyang
 */
public class ECCTest {
    ECC ecc = new ECC(256); // Generate a 256-bit ECC key pair. Calls generateKeys(bits) to create keys including privateKey and publicKey.

    /**
     * Test the encryption functionality: convert plaintext to ciphertext and output relevant encryption data.
     */
    @Test
    void testEncrypt() {
        String textToEncrypt = "Elliptic Curve Cryptography";

        ECC.ECPoint[] cipherText = ecc.encrypt(textToEncrypt); // Perform encryption

        // Output private key information
        System.out.println("Private Key: " + ecc.getPrivateKey());

        // Output elliptic curve parameters
        ECC.EllipticCurve curve = ecc.getCurve();
        System.out.println("Elliptic Curve Parameters:");
        System.out.println("a: " + curve.getA());
        System.out.println("b: " + curve.getB());
        System.out.println("p: " + curve.getP());
        System.out.println("Base Point G: " + curve.getBasePoint());

        // Verify that the ciphertext is not empty
        assertEquals(cipherText.length, 2); // Check if the ciphertext contains two points (R and S)

        // Output the encrypted coordinate points
        System.out.println("Encrypted Points:");
        for (ECC.ECPoint point : cipherText) {
            System.out.println(point); // Calls ECPoint's toString() method
        }
    }

    /**
     * Test the decryption functionality: convert ciphertext back to plaintext using known private key and elliptic curve parameters.
     */
    @Test
    void testDecryptWithKnownValues() {
        // 1. Define the known private key
        BigInteger knownPrivateKey = new BigInteger("28635978664199231399690075483195602260051035216440375973817268759912070302603");

        // 2. Define the known elliptic curve parameters
        BigInteger a = new BigInteger("64505295837372135469230827475895976532873592609649950000895066186842236488761"); // Replace with known a value
        BigInteger b = new BigInteger("89111668838830965251111555638616364203833415376750835901427122343021749874324"); // Replace with known b value
        BigInteger p = new BigInteger("107276428198310591598877737561885175918069075479103276920057092968372930219921"); // Replace with known p value
        ECC.ECPoint basePoint = new ECC.ECPoint(new BigInteger("4"), new BigInteger("8")); // Replace with known base point coordinates

        // 3. Create the elliptic curve object
        ECC.EllipticCurve curve = new ECC.EllipticCurve(a, b, p, basePoint);

        // 4. Define the known ciphertext containing two ECPoints (R, S)
        ECC.ECPoint rPoint = new ECC.ECPoint(new BigInteger("103077584019003058745849614420912636617007257617156724481937620119667345237687"), new BigInteger("68193862907937248121971710522760893811582068323088661566426323952783362061817"));
        ECC.ECPoint sPoint = new ECC.ECPoint(new BigInteger("31932232426664380635434632300383525435115368414929679432313910646436992147798"), new BigInteger("77299754382292904069123203569944908076819220797512755280123348910207308129766"));
        ECC.ECPoint[] cipherText = new ECC.ECPoint[] {rPoint, sPoint};

        // 5. Create an ECC instance and set the private key and curve parameters
        ecc.setPrivateKey(knownPrivateKey); // Use setter method to set the private key
        ecc.setCurve(curve); // Use setter method to set the elliptic curve

        // 6. Decrypt the known ciphertext
        String decryptedMessage = ecc.decrypt(cipherText);

        // 7. Compare the decrypted plaintext with the expected value
        String expectedMessage = "Elliptic Curve Cryptography"; // Expected plaintext
        assertEquals(expectedMessage, decryptedMessage);
    }

    /**
     * Test that encrypting the same plaintext with ECC produces different ciphertexts.
     */
    @Test
    void testCipherTextRandomness() {
        String message = "Elliptic Curve Cryptography";

        ECC.ECPoint[] cipherText1 = ecc.encrypt(message);
        ECC.ECPoint[] cipherText2 = ecc.encrypt(message);

        assertNotEquals(cipherText1, cipherText2); // Ensure that the two ciphertexts are different
    }

    /**
     * Test the entire ECC encryption and decryption process.
     */
    @Test
    void testECCEncryptionAndDecryption() {
        String textToEncrypt = "Elliptic Curve Cryptography";
        ECC.ECPoint[] cipherText = ecc.encrypt(textToEncrypt);
        String decryptedText = ecc.decrypt(cipherText);
        assertEquals(textToEncrypt, decryptedText); // Verify that the decrypted text matches the original text
    }
}
