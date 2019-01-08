package src.main.java.com.crypto.hash;

import java.nio.ByteBuffer;

/**
 * <p>This class implements the Secure Hash Algorithm 2 family, namely SHA-224,
 * SHA-256, SHA-384 and SHA-512. These algorithms produce a compressed
 * representation with fixed length of any message provided by the user.
 * The result is called a message digest. Any change to the message will
 * result in a different message digest.
 * <p>SHA-2 has many possible areas of application. For example, someone
 * can determine if a file was manipulated, by comparing the message digest
 * of the original file with the message digest of the file in question.
 * Another example is the use of SHA-256 in the Proof-of-work algorithm of Bitcoin.
 * <p>
 * This implementation is based on the RFC 6234 specification. The original
 * specification of SHA-2 is defined in FIPS PUB 180-4. Due to the U.S.
 * government shutdown by the end of 2018 the original specification was offline.
 *
 * @see <a href="https://tools.ietf.org/html/rfc6234">RFC 6234</a>
 * @see <a href="http://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.180-4.pdf">FIPS PUB 180-4</a>
 */
public final class Sha2 {

    /**
     * <p>Returns a SHA-224 message digest with a fixed length of 224 bit (28 byte).
     * By specification, the user-provided data can have a length of 0 &lt;= L &lt; 2^61 byte.
     * The JVM, though, allows an array with a maximum length of approximately
     * Integer.MAX_VALUE.</p>
     *
     * @param data the data/message to be digested
     * @return the message digest with a fixed length of 224 bit (28 byte)
     */
    public static String SHA224(byte[] data) {
        final int[] initialHash = {
                0xc1059ed8, 0x367cd507, 0x3070dd17, 0xf70e5939,
                0xffc00b31, 0x68581511, 0x64f98fa7, 0xbefa4fa4
        };

        int[] finalHash = digest(data, initialHash);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < finalHash.length - 1; i++) {
            builder.append(String.format("%1$08x", finalHash[i]));
        }

        return builder.toString();
    }

    /**
     * <p>Returns a SHA-256 message digest with a fixed length of 256 bit (32 byte).<p>
     *
     * @param data the data/message to be digested
     * @return the message digest with a fixed length of 256 bit (32 byte)
     * @see src.main.java.com.crypto.hash.Sha2#SHA224(byte[]) SHA224()
     */
    public static String SHA256(byte[] data) {
        final int[] initialHash = {
                0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a,
                0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19
        };

        int[] finalHash = digest(data, initialHash);

        StringBuilder builder = new StringBuilder();
        for (int aFinalHash : finalHash) {
            builder.append(String.format("%1$08x", aFinalHash));
        }

        return builder.toString();
    }

    /**
     * <p>Returns a SHA-384 message digest with a fixed length of 384 bit (48 byte).
     * By specification, the user-provided data can have a length of 0 &lt;= L &lt; 2^125 byte.
     * The JVM, though, allows an array with a maximum length of approximately
     * Integer.MAX_VALUE.</p>
     *
     * @param data the data/message to be digested
     * @return the message digest with a fixed length of 384 bit (48 byte)
     */
    public static String SHA384(byte[] data) {
        final long[] initialHash = {
                0xcbbb9d5dc1059ed8L, 0x629a292a367cd507L, 0x9159015a3070dd17L, 0x152fecd8f70e5939L,
                0x67332667ffc00b31L, 0x8eb44a8768581511L, 0xdb0c2e0d64f98fa7L, 0x47b5481dbefa4fa4L
        };

        long[] finalHash = digest(data, initialHash);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < finalHash.length - 2; i++) {
            builder.append(String.format("%1$016x", finalHash[i]));
        }

        return builder.toString();
    }

    /**
     * <p>Returns a SHA-512 message digest with a fixed length of 512 bit (64 byte).</p>
     *
     * @param data the data/message to be digested
     * @return the message digest with a fixed length of 512 bit (64 byte)
     * @see src.main.java.com.crypto.hash.Sha2#SHA384(byte[]) SHA384()
     */
    public static String SHA512(byte[] data) {
        final long[] initialHash = {
                0x6a09e667f3bcc908L, 0xbb67ae8584caa73bL, 0x3c6ef372fe94f82bL, 0xa54ff53a5f1d36f1L,
                0x510e527fade682d1L, 0x9b05688c2b3e6c1fL, 0x1f83d9abfb41bd6bL, 0x5be0cd19137e2179L
        };

        long[] finalHash = digest(data, initialHash);

        StringBuilder builder = new StringBuilder();
        for (long aFinalHash : finalHash) {
            builder.append(String.format("%1$016x", aFinalHash));
        }
        return builder.toString();
    }


    /**
     * <p>Returns an integer array, which holds the raw message digest.</p>
     * <p>This method is wrapped by SHA224() and SHA256(). Both algorithms differ
     * only in two points: the initialization hashes are different and for SHA-224
     * the raw message digest is truncated by 1 byte.</p>
     *
     * @param data the data/message to be digested
     * @param hash the initial hash value, which in the process gets used
     *             for the intermediate hashes
     * @return the raw message digest
     */
    private static int[] digest(byte[] data, int[] hash) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        byte[] padding = pad(data, 64);

        ByteBuffer buffer = ByteBuffer.allocate(data.length + padding.length);
        buffer.put(data).put(padding);
        buffer.rewind();

        while (buffer.hasRemaining()) {
            int[] messageBlock = new int[16];
            for (int i = 0; i < 16; i++) {
                messageBlock[i] = buffer.getInt();
            }
            hashBlock(messageBlock, hash);
        }

        return hash;
    }

    /**
     * <p>Returns an integer array, which holds the raw message digest.</p>
     * <p>This method is wrapped by SHA384() and SHA512(). Both algorithms differ
     * only in two points: the initialization hashes are different and for SHA-384
     * the raw message digest is truncated by 2 byte.</p>
     *
     * @param data the data/message to be digested
     * @param hash the initial hash value, which in the process gets used
     *             for the intermediate hashes
     * @return the raw message digest
     */
    private static long[] digest(byte[] data, long[] hash) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        byte[] padding = pad(data, 128);

        ByteBuffer buffer = ByteBuffer.allocate(data.length + padding.length);
        buffer.put(data).put(padding);
        buffer.rewind();

        while (buffer.hasRemaining()) {
            long[] messageBlock = new long[16];
            for (int i = 0; i < 16; i++) {
                messageBlock[i] = buffer.getLong();
            }
            hashBlock(messageBlock, hash);
        }

        return hash;
    }

    /**
     * <p>Pads the user-provided data.</p>
     *
     * @param data      the data/message to be digested
     * @param blockSize the size of a data block (64 or 128 byte)
     * @return the padding for the data
     * @see <a href="https://tools.ietf.org/html/rfc6234#section-4">RFC 6234 - Message padding</a>
     */
    private static byte[] pad(byte[] data, int blockSize) {
        byte[] padding;
        int lastBlockLength = data.length % blockSize;
        if (lastBlockLength + 1 > (blockSize / 8) * 7) {
            padding = new byte[blockSize * 2 - lastBlockLength];
        } else {
            padding = new byte[blockSize - lastBlockLength];
        }

        ByteBuffer buffer = ByteBuffer.wrap(padding);
        buffer.put((byte) 0b1000_0000);
        while (buffer.position() < buffer.capacity() - 8) {
            buffer.put((byte) 0);
        }
        buffer.putLong((long) data.length * 8);

        return padding;
    }

    /**
     * Scrambles data blocks in a deterministic way.
     *
     * @param dataBlock the data blocks to be scrambled
     * @param hash      the resulting hash
     * @see <a href="https://tools.ietf.org/html/rfc6234#section-6.2">SHA-224 and SHA-256 Processing</a>
     */
    private static void hashBlock(int[] dataBlock, int[] hash) {
        int[] W = new int[64];
        int[] reg = new int[8];
        int temp1;
        int temp2;

        // Prepare the message schedule W
        for (int i = 0; i < 16; i++) {
            W[i] = dataBlock[i];
        }
        for (int i = 16; i < 64; i++) {
            W[i] = SSIG1(W[i - 2]) + W[i - 7] + SSIG0(W[i - 15]) + W[i - 16];
        }

        // Initialize the working variables
        for (int i = 0; i < 8; i++) {
            reg[i] = hash[i];
        }

        // Perform the main hash computation
        for (int i = 0; i < 64; i++) {
            temp1 = reg[7] + BSIG1(reg[4]) + CH(reg[4], reg[5], reg[6]) + K_int[i] + W[i];
            temp2 = BSIG0(reg[0]) + MAJ(reg[0], reg[1], reg[2]);
            reg[7] = reg[6];
            reg[6] = reg[5];
            reg[5] = reg[4];
            reg[4] = reg[3] + temp1;
            reg[3] = reg[2];
            reg[2] = reg[1];
            reg[1] = reg[0];
            reg[0] = temp1 + temp2;
        }

        // Compute the intermediate hash value H(i)
        for (int i = 0; i < 8; i++) {
            hash[i] = reg[i] + hash[i];
        }
    }

    /**
     * Scrambles data blocks in a deterministic way.
     *
     * @param dataBlock the data blocks to be scrambled
     * @param hash      the resulting hash
     * @see <a href="https://tools.ietf.org/html/rfc6234#section-6.4">SHA-384 and SHA-512 Processing</a>
     */
    private static void hashBlock(long[] dataBlock, long[] hash) {
        long[] W = new long[80];
        long[] reg = new long[8];
        long temp1;
        long temp2;

        // Prepare the message schedule W
        for (int i = 0; i < 16; i++) {
            W[i] = dataBlock[i];
        }
        for (int i = 16; i < 80; i++) {
            W[i] = SSIG1(W[i - 2]) + W[i - 7] + SSIG0(W[i - 15]) + W[i - 16];
        }

        // Initialize the working variables
        for (int i = 0; i < 8; i++) {
            reg[i] = hash[i];
        }

        // Perform the main hash computation
        for (int i = 0; i < 80; i++) {
            temp1 = reg[7] + BSIG1(reg[4]) + CH(reg[4], reg[5], reg[6]) + K_long[i] + W[i];
            temp2 = BSIG0(reg[0]) + MAJ(reg[0], reg[1], reg[2]);
            reg[7] = reg[6];
            reg[6] = reg[5];
            reg[5] = reg[4];
            reg[4] = reg[3] + temp1;
            reg[3] = reg[2];
            reg[2] = reg[1];
            reg[1] = reg[0];
            reg[0] = temp1 + temp2;
        }

        // Compute the intermediate hash value H(i)
        for (int i = 0; i < 8; i++) {
            hash[i] = reg[i] + hash[i];
        }
    }

    /*
     * Functions and Constants used
     * https://tools.ietf.org/html/rfc6234#section-5
     */

    private static final int[] K_int = {
            0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5,
            0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3,
            0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc,
            0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7,
            0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13,
            0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3,
            0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5,
            0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208,
            0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
    };

    private static final long[] K_long = {
            0x428a2f98d728ae22L, 0x7137449123ef65cdL, 0xb5c0fbcfec4d3b2fL, 0xe9b5dba58189dbbcL,
            0x3956c25bf348b538L, 0x59f111f1b605d019L, 0x923f82a4af194f9bL, 0xab1c5ed5da6d8118L,
            0xd807aa98a3030242L, 0x12835b0145706fbeL, 0x243185be4ee4b28cL, 0x550c7dc3d5ffb4e2L,
            0x72be5d74f27b896fL, 0x80deb1fe3b1696b1L, 0x9bdc06a725c71235L, 0xc19bf174cf692694L,
            0xe49b69c19ef14ad2L, 0xefbe4786384f25e3L, 0x0fc19dc68b8cd5b5L, 0x240ca1cc77ac9c65L,
            0x2de92c6f592b0275L, 0x4a7484aa6ea6e483L, 0x5cb0a9dcbd41fbd4L, 0x76f988da831153b5L,
            0x983e5152ee66dfabL, 0xa831c66d2db43210L, 0xb00327c898fb213fL, 0xbf597fc7beef0ee4L,
            0xc6e00bf33da88fc2L, 0xd5a79147930aa725L, 0x06ca6351e003826fL, 0x142929670a0e6e70L,
            0x27b70a8546d22ffcL, 0x2e1b21385c26c926L, 0x4d2c6dfc5ac42aedL, 0x53380d139d95b3dfL,
            0x650a73548baf63deL, 0x766a0abb3c77b2a8L, 0x81c2c92e47edaee6L, 0x92722c851482353bL,
            0xa2bfe8a14cf10364L, 0xa81a664bbc423001L, 0xc24b8b70d0f89791L, 0xc76c51a30654be30L,
            0xd192e819d6ef5218L, 0xd69906245565a910L, 0xf40e35855771202aL, 0x106aa07032bbd1b8L,
            0x19a4c116b8d2d0c8L, 0x1e376c085141ab53L, 0x2748774cdf8eeb99L, 0x34b0bcb5e19b48a8L,
            0x391c0cb3c5c95a63L, 0x4ed8aa4ae3418acbL, 0x5b9cca4f7763e373L, 0x682e6ff3d6b2b8a3L,
            0x748f82ee5defb2fcL, 0x78a5636f43172f60L, 0x84c87814a1f0ab72L, 0x8cc702081a6439ecL,
            0x90befffa23631e28L, 0xa4506cebde82bde9L, 0xbef9a3f7b2c67915L, 0xc67178f2e372532bL,
            0xca273eceea26619cL, 0xd186b8c721c0c207L, 0xeada7dd6cde0eb1eL, 0xf57d4f7fee6ed178L,
            0x06f067aa72176fbaL, 0x0a637dc5a2c898a6L, 0x113f9804bef90daeL, 0x1b710b35131c471bL,
            0x28db77f523047d84L, 0x32caab7b40c72493L, 0x3c9ebe0a15c9bebcL, 0x431d67c49c100d4cL,
            0x4cc5d4becb3e42b6L, 0x597f299cfc657e2aL, 0x5fcb6fab3ad6faecL, 0x6c44198c4a475817L
    };

    private static int CH(int x, int y, int z) {
        return (x & y) ^ ((~x) & z);
    }

    private static long CH(long x, long y, long z) {
        return (x & y) ^ ((~x) & z);
    }

    private static int MAJ(int x, int y, int z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    private static long MAJ(long x, long y, long z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    private static int BSIG0(int x) {
        return ROTR(x, 2) ^ ROTR(x, 13) ^ ROTR(x, 22);
    }

    private static long BSIG0(long x) {
        return ROTR(x, 28) ^ ROTR(x, 34) ^ ROTR(x, 39);
    }

    private static int BSIG1(int x) {
        return ROTR(x, 6) ^ ROTR(x, 11) ^ ROTR(x, 25);
    }

    private static long BSIG1(long x) {
        return ROTR(x, 14) ^ ROTR(x, 18) ^ ROTR(x, 41);
    }

    private static int SSIG0(int x) {
        return ROTR(x, 7) ^ ROTR(x, 18) ^ (x >>> 3);
    }

    private static long SSIG0(long x) {
        return ROTR(x, 1) ^ ROTR(x, 8) ^ (x >>> 7);
    }

    private static int SSIG1(int x) {
        return ROTR(x, 17) ^ ROTR(x, 19) ^ (x >>> 10);
    }

    private static long SSIG1(long x) {
        return ROTR(x, 19) ^ ROTR(x, 61) ^ (x >>> 6);
    }

    private static int ROTR(int x, int n) {
        return (x >>> n) | (x << (32 - n));
    }

    private static long ROTR(long x, long n) {
        return (x >>> n) | (x << (64 - n));
    }
}
