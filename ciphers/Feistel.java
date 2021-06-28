import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Feistel {
    private static final int rounds = 16;
    private static final int blockSize = 8;


    public static void main(String[] args) {
        if (args.length < 1) {
            printHelp();
        }
        if (args.length > 1) {
            System.out.print("WARNING: the following arguments have been ignored: ");
            System.out.println(Arrays.toString(Arrays.copyOfRange(args, 1, args.length)));
        }
        String password = args[0];
        String hashedPassword = sha256(password);
        String hashedHashedPassword = sha256(hashedPassword);
        byte[] key = hexToBytes(hashedPassword.concat(hashedHashedPassword));
        byte[] plain = new byte[0];
        byte[] encrypted = new byte[0];

        try {
            plain = readBytes();
        } catch (IOException e) {
            System.err.println("Could not read bytes from input:");
            e.printStackTrace();
            System.exit(-1);
        }

        //this assumes the input is a multiple of blockSize (=8) bytes long,
        //any remaining bytes will not be read anyway due to the truncation of the division
        for (int i = 0; i < plain.length / blockSize; i++) {
            try {
                encrypted = arrayConcat(encrypted,
                        feistelCipher(Arrays.copyOfRange(plain, i * blockSize, (i + 1) * blockSize), key)
                );
            } catch (IOException e) {
                System.err.println("Could not concat next encrypted block to current encrypted bytes:");
                e.printStackTrace();
                System.exit(-1);
            }
        }


        try {
            System.out.write(encrypted);
        } catch (IOException e) {
            System.err.println("Could not print encrypted bytes:");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Interprets a string as a list of hex values and returns them as an array
     *
     * @param byteRepresentation the string that represents the bytes
     * @return a byte array containing the bytes that the given string represents
     */
    private static byte[] hexToBytes(String byteRepresentation) {
        byte[] bytes = new byte[byteRepresentation.length() / 2];
        for (int src = 0, dst = 0; dst < bytes.length; ++dst) {
            int hi = Character.digit(byteRepresentation.charAt(src++), 16);
            int lo = Character.digit(byteRepresentation.charAt(src++), 16);
            if ((hi < 0) || (lo < 0))
                throw new IllegalArgumentException();
            bytes[dst] = (byte) (hi << 4 | lo);
        }
        return bytes;
    }

    /**
     * reads bytes from the input until end of file is reached
     * this means that this method only ever returns when we are reading a redirected file,
     * not when the input comes directly from the console!
     *
     * @return all bytes that were read
     * @throws IOException can occur in System.in.read()
     */
    private static byte[] readBytes() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int plainLength = 0;
        int nextByte = System.in.read();

        while (nextByte != -1) {
            plainLength++;
            bos.write(nextByte);
            nextByte = System.in.read();
        }

        return Arrays.copyOf(bos.toByteArray(), plainLength);
    }


    /**
     * applies a Feistel cipher to the given plaintext, with a given amount of rounds
     *
     * @param plain the plaintext to apply the Feistel cipher to,
     *              this should always be blockSize (=8) bytes long (the rest will be ignored anyway)
     * @param key   the key to use for the Feistel cipher, should be >= rounds * blockSize/2 (=4) bytes long
     * @return the encrypted text
     */
    private static byte[] feistelCipher(byte[] plain, byte[] key) {
        if (plain.length < blockSize) {
            System.err.println("Plaintext too short, aborting Feistel cipher");
            return null;
        }
        if (Feistel.rounds * blockSize / 2 > key.length) {
            System.err.println("Key too short, aborting Feistel cipher");
            return null;
        }

        byte[] lh = Arrays.copyOfRange(plain, 0, blockSize / 2);
        byte[] rh = Arrays.copyOfRange(plain, blockSize / 2, blockSize);

        for (int i = 0; i < Feistel.rounds; i++) {
            byte[] temp = Arrays.copyOf(rh, rh.length);
            rh = xor(lh, Arrays.copyOfRange(key, i * blockSize / 2, (i + 1) * blockSize / 2));
            lh = temp;
        }

        try {
            return arrayConcat(lh, rh);
        } catch (IOException e) {
            System.err.println("Could not concat lh and rh in feistel process");
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Computes the sha256 hash of a given string
     *
     * @param plain the plain string to hash
     * @return the hashed string
     */
    private static String sha256(String plain) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(plain.getBytes(StandardCharsets.UTF_8));
            result = bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * concatenates 2 byte[] together
     *
     * @param arr1 the first array
     * @param arr2 the second array
     * @return the concatenation of arr1 and arr2
     * @throws IOException can occur in ByteArrayOutputStream.write();
     */
    private static byte[] arrayConcat(byte[] arr1, byte[] arr2) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(arr1);
        bos.write(arr2);

        return Arrays.copyOf(bos.toByteArray(), arr1.length + arr2.length);
    }


    /**
     * Puts the literal value of the bytes in a string,
     * whereas constructing a new string with bytes as an argument to the constructor
     * would 'translate' the bytes to characters
     *
     * @param bytes the byte array to transliterate to a string
     * @return the string representing the given byte array
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }


    /**
     * performs an exclusive or operation on 2 sets of bytes
     *
     * @param bytes1 the first set of bytes, must be blockSize/2 (=4) bytes long
     * @param bytes2 the second set of bytes, must be blockSize/2 (=4) bytes long
     * @return the xor of the two arrays of bytes
     */
    private static byte[] xor(byte[] bytes1, byte[] bytes2) {
        byte[] xorResult = new byte[blockSize / 2];
        for (int i = 0; i < blockSize / 2; i++) {
            xorResult[i] = (byte) (bytes1[i] ^ bytes2[i]);
        }

        return xorResult;
    }


    private static void printHelp() {
        printHelp(0);
    }

    private static void printHelp(int exitCode) {
        System.out.println(
                "Usage: Feistel password\n" +
                        "\tpassword: the password to use for the encryption/decryption.\n"
        );
        System.exit(exitCode);
    }
}
