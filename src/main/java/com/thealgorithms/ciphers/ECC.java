package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * ECC - Elliptic Curve Cryptography
 * Elliptic Curve Cryptography is a public-key cryptography method that uses the algebraic structure of
 * elliptic curves over finite fields. ECC provides a higher level of security with smaller key sizes compared
 * to other public-key methods like RSA, making it particularly suitable for environments where computational
 * resources are limited, such as mobile devices and embedded systems.
 *
 * This class implements elliptic curve cryptography, providing encryption and decryption
 * functionalities based on public and private key pairs.
 *
 * @author xuyang
 */
public class ECC {

    private BigInteger privateKey; // Private key used for decryption
    private ECPoint publicKey; // Public key used for encryption
    private EllipticCurve curve; // Elliptic curve used in cryptography
    private ECPoint basePoint; // Base point G on the elliptic curve

    public ECC(int bits) {
        generateKeys(bits); // Generates public-private key pair
    }

    public EllipticCurve getCurve() {
        return curve; // Returns the elliptic curve
    }

    public void setCurve(EllipticCurve curve) {
        this.curve = curve;
    }

    // Getter and Setter for private key
    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Encrypts the message using the public key.
     * The message is transformed into an ECPoint and encrypted with elliptic curve operations.
     *
     * @param message The plain message to be encrypted
     * @return The encrypted message as an array of ECPoints (R, S)
     */
    public ECPoint[] encrypt(String message) {
        BigInteger m = new BigInteger(message.getBytes()); // Convert message to BigInteger
        SecureRandom r = new SecureRandom(); // Generate random value for k
        BigInteger k = new BigInteger(curve.getFieldSize(), r); // Generate random scalar k

        // Calculate point r = k * G, where G is the base point
        ECPoint rPoint = basePoint.multiply(k, curve.getP(), curve.getA());

        // Calculate point s = k * publicKey + encodedMessage
        ECPoint sPoint = publicKey.multiply(k, curve.getP(), curve.getA()).add(curve.encodeMessage(m), curve.getP(), curve.getA());

        return new ECPoint[] {rPoint, sPoint}; // Return encrypted message as two ECPoints
    }

    /**
     * Decrypts the encrypted message using the private key.
     * The decryption process is the reverse of encryption, recovering the original message.
     *
     * @param encryptedMessage The encrypted message as an array of ECPoints (R, S)
     * @return The decrypted plain message as a String
     */
    public String decrypt(ECPoint[] encryptedMessage) {
        ECPoint rPoint = encryptedMessage[0]; // First part of ciphertext
        ECPoint sPoint = encryptedMessage[1]; // Second part of ciphertext

        // Perform decryption: s - r * privateKey
        ECPoint decodedMessage = sPoint.subtract(rPoint.multiply(privateKey, curve.getP(), curve.getA()), curve.getP(), curve.getA());

        BigInteger m = curve.decodeMessage(decodedMessage); // Decode the message from ECPoint

        return new String(m.toByteArray()); // Convert BigInteger back to String
    }

    /**
     * Generates a new public-private key pair for encryption and decryption.
     *
     * @param bits The size (in bits) of the keys to generate
     */
    public final void generateKeys(int bits) {
        SecureRandom r = new SecureRandom();
        curve = new EllipticCurve(bits); // Initialize a new elliptic curve
        basePoint = curve.getBasePoint(); // Set the base point G

        // Generate private key as a random BigInteger
        privateKey = new BigInteger(bits, r);

        // Generate public key as the point publicKey = privateKey * G
        publicKey = basePoint.multiply(privateKey, curve.getP(), curve.getA());
    }

    /**
     * Class representing an elliptic curve with the form y^2 = x^3 + ax + b.
     */
    public static class EllipticCurve {
        private final BigInteger a; // Coefficient a in the curve equation
        private final BigInteger b; // Coefficient b in the curve equation
        private final BigInteger p; // Prime number p, defining the finite field
        private final ECPoint basePoint; // Base point G on the curve

        // Constructor with explicit parameters for a, b, p, and base point
        public EllipticCurve(BigInteger a, BigInteger b, BigInteger p, ECPoint basePoint) {
            this.a = a;
            this.b = b;
            this.p = p;
            this.basePoint = basePoint;
        }

        // Constructor that randomly generates the curve parameters
        public EllipticCurve(int bits) {
            SecureRandom r = new SecureRandom();
            this.p = BigInteger.probablePrime(bits, r); // Random prime p
            this.a = new BigInteger(bits, r); // Random coefficient a
            this.b = new BigInteger(bits, r); // Random coefficient b
            this.basePoint = new ECPoint(BigInteger.valueOf(4), BigInteger.valueOf(8)); // Fixed base point G
        }

        public ECPoint getBasePoint() {
            return basePoint;
        }

        public BigInteger getP() {
            return p;
        }

        public BigInteger getA() {
            return a;
        }

        public BigInteger getB() {
            return b;
        }

        public int getFieldSize() {
            return p.bitLength();
        }

        public ECPoint encodeMessage(BigInteger message) {
            // Simple encoding of a message as an ECPoint (this is a simplified example)
            return new ECPoint(message, message);
        }

        public BigInteger decodeMessage(ECPoint point) {
            return point.getX(); // Decode the message from ECPoint (simplified)
        }
    }

    /**
     * Class representing a point on the elliptic curve.
     */
    public static class ECPoint {
        private final BigInteger x; // X-coordinate of the point
        private final BigInteger y; // Y-coordinate of the point

        public ECPoint(BigInteger x, BigInteger y) {
            this.x = x;
            this.y = y;
        }

        public BigInteger getX() {
            return x;
        }

        public BigInteger getY() {
            return y;
        }

        @Override
        public String toString() {
            return "ECPoint(x=" + x.toString() + ", y=" + y.toString() + ")";
        }

        /**
         * Add two points on the elliptic curve.
         */
        public ECPoint add(ECPoint other, BigInteger p, BigInteger a) {
            if (this.x.equals(BigInteger.ZERO) && this.y.equals(BigInteger.ZERO)) {
                return other; // If this point is the identity, return the other point
            }
            if (other.x.equals(BigInteger.ZERO) && other.y.equals(BigInteger.ZERO)) {
                return this; // If the other point is the identity, return this point
            }

            BigInteger lambda;
            if (this.equals(other)) {
                // Special case: point doubling
                lambda = this.x.pow(2).multiply(BigInteger.valueOf(3)).add(a).multiply(this.y.multiply(BigInteger.valueOf(2)).modInverse(p)).mod(p);
            } else {
                // General case: adding two different points
                lambda = other.y.subtract(this.y).multiply(other.x.subtract(this.x).modInverse(p)).mod(p);
            }

            BigInteger xr = lambda.pow(2).subtract(this.x).subtract(other.x).mod(p);
            BigInteger yr = lambda.multiply(this.x.subtract(xr)).subtract(this.y).mod(p);

            return new ECPoint(xr, yr);
        }

        /**
         * Subtract two points on the elliptic curve.
         */
        public ECPoint subtract(ECPoint other, BigInteger p, BigInteger a) {
            ECPoint negOther = new ECPoint(other.x, p.subtract(other.y)); // Negate the Y coordinate
            return this.add(negOther, p, a); // Add the negated point
        }

        /**
         * Multiply a point by a scalar (repeated addition).
         */
        public ECPoint multiply(BigInteger k, BigInteger p, BigInteger a) {
            ECPoint result = new ECPoint(BigInteger.ZERO, BigInteger.ZERO); // Identity point
            ECPoint addend = this;

            while (k.signum() > 0) {
                if (k.testBit(0)) {
                    result = result.add(addend, p, a); // Add the current point
                }
                addend = addend.add(addend, p, a); // Double the point
                k = k.shiftRight(1); // Divide k by 2
            }

            return result;
        }
    }
}
