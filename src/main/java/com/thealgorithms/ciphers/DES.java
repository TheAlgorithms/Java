package com.thealgorithms.ciphers;

/**
 * This class is build to demonstrate the application of the DES-algorithm
 * (https://en.wikipedia.org/wiki/Data_Encryption_Standard) on a plain English message. The supplied
 * key must be in form of a 64 bit binary String.
 */
public class DES {

    private String key;
    private String subKeys[];

    private void sanitize(String key) {
        int length = key.length();
        if (length != 64) {
            throw new IllegalArgumentException("DES key must be supplied as a 64 character binary string");
        }
    }

    DES(String key) {
        sanitize(key);
        this.key = key;
        subKeys = getSubkeys(key);
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        sanitize(key);
        this.key = key;
    }

    // Permutation table to convert initial 64 bit key to 56 bit key
    private static int[] PC1 = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};

    // Lookup table used to shift the initial key, in order to generate the subkeys
    private static int[] KEY_SHIFTS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    // Table to convert the 56 bit subkeys to 48 bit subkeys
    private static int[] PC2 = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};

    // Initial permutatation of each 64 but message block
    private static int[] IP = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};

    // Expansion table to convert right half of message blocks from 32 bits to 48 bits
    private static int[] expansion = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};

    // The eight substitution boxes are defined below
    private static int[][] s1 = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7}, {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8}, {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0}, {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};

    private static int[][] s2 = {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10}, {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5}, {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15}, {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};

    private static int[][] s3 = {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8}, {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1}, {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7}, {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};

    private static int[][] s4 = {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15}, {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9}, {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4}, {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};

    private static int[][] s5 = {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9}, {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6}, {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14}, {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};

    private static int[][] s6 = {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11}, {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8}, {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6}, {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};

    private static int[][] s7 = {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1}, {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6}, {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2}, {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};

    private static int[][] s8 = {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7}, {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2}, {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8}, {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};

    private static int[][][] s = {s1, s2, s3, s4, s5, s6, s7, s8};

    // Permutation table, used in the feistel function post s-box usage
    static int[] permutation = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};

    // Table used for final inversion of the message box after 16 rounds of Feistel Function
    static int[] IPinverse = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25};

    private String[] getSubkeys(String originalKey) {
        StringBuilder permutedKey = new StringBuilder(); // Initial permutation of keys via PC1
        int i, j;
        for (i = 0; i < 56; i++) {
            permutedKey.append(originalKey.charAt(PC1[i] - 1));
        }
        String subKeys[] = new String[16];
        String initialPermutedKey = permutedKey.toString();
        String C0 = initialPermutedKey.substring(0, 28), D0 = initialPermutedKey.substring(28);

        // We will now operate on the left and right halves of the permutedKey
        for (i = 0; i < 16; i++) {
            String Cn = C0.substring(KEY_SHIFTS[i]) + C0.substring(0, KEY_SHIFTS[i]);
            String Dn = D0.substring(KEY_SHIFTS[i]) + D0.substring(0, KEY_SHIFTS[i]);
            subKeys[i] = Cn + Dn;
            C0 = Cn; // Re-assign the values to create running permutation
            D0 = Dn;
        }

        // Let us shrink the keys to 48 bits (well, characters here) using PC2
        for (i = 0; i < 16; i++) {
            String key = subKeys[i];
            permutedKey.setLength(0);
            for (j = 0; j < 48; j++) {
                permutedKey.append(key.charAt(PC2[j] - 1));
            }
            subKeys[i] = permutedKey.toString();
        }

        return subKeys;
    }

    private String XOR(String a, String b) {
        int i, l = a.length();
        StringBuilder xor = new StringBuilder();
        for (i = 0; i < l; i++) {
            int firstBit = a.charAt(i) - 48; // 48 is '0' in ascii
            int secondBit = b.charAt(i) - 48;
            xor.append((firstBit ^ secondBit));
        }
        return xor.toString();
    }

    private String createPaddedString(String s, int desiredLength, char pad) {
        int i, l = s.length();
        StringBuilder paddedString = new StringBuilder();
        int diff = desiredLength - l;
        for (i = 0; i < diff; i++) {
            paddedString.append(pad);
        }
        return paddedString.toString();
    }

    private String pad(String s, int desiredLength) {
        return createPaddedString(s, desiredLength, '0') + s;
    }

    private String padLast(String s, int desiredLength) {
        return s + createPaddedString(s, desiredLength, '\u0000');
    }

    private String feistel(String messageBlock, String key) {
        int i;
        StringBuilder expandedKey = new StringBuilder();
        for (i = 0; i < 48; i++) {
            expandedKey.append(messageBlock.charAt(expansion[i] - 1));
        }
        String mixedKey = XOR(expandedKey.toString(), key);
        StringBuilder substitutedString = new StringBuilder();

        // Let us now use the s-boxes to transform each 6 bit (length here) block to 4 bits
        for (i = 0; i < 48; i += 6) {
            String block = mixedKey.substring(i, i + 6);
            int row = (block.charAt(0) - 48) * 2 + (block.charAt(5) - 48);
            int col = (block.charAt(1) - 48) * 8 + (block.charAt(2) - 48) * 4 + (block.charAt(3) - 48) * 2 + (block.charAt(4) - 48);
            String substitutedBlock = pad(Integer.toBinaryString(s[i / 6][row][col]), 4);
            substitutedString.append(substitutedBlock);
        }

        StringBuilder permutedString = new StringBuilder();
        for (i = 0; i < 32; i++) {
            permutedString.append(substitutedString.charAt(permutation[i] - 1));
        }

        return permutedString.toString();
    }

    private String encryptBlock(String message, String keys[]) {
        StringBuilder permutedMessage = new StringBuilder();
        int i;
        for (i = 0; i < 64; i++) {
            permutedMessage.append(message.charAt(IP[i] - 1));
        }
        String L0 = permutedMessage.substring(0, 32), R0 = permutedMessage.substring(32);

        // Iterate 16 times
        for (i = 0; i < 16; i++) {
            String Ln = R0; // Previous Right block
            String Rn = XOR(L0, feistel(R0, keys[i]));
            L0 = Ln;
            R0 = Rn;
        }

        String combinedBlock = R0 + L0; // Reverse the 16th block
        permutedMessage.setLength(0);
        for (i = 0; i < 64; i++) {
            permutedMessage.append(combinedBlock.charAt(IPinverse[i] - 1));
        }
        return permutedMessage.toString();
    }

    // To decode, we follow the same process as encoding, but with reversed keys
    private String decryptBlock(String message, String keys[]) {
        String reversedKeys[] = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            reversedKeys[i] = keys[keys.length - i - 1];
        }
        return encryptBlock(message, reversedKeys);
    }

    /**
     * @param message Message to be encrypted
     * @return The encrypted message, as a binary string
     */
    public String encrypt(String message) {
        StringBuilder encryptedMessage = new StringBuilder();
        int l = message.length(), i, j;
        if (l % 8 != 0) {
            int desiredLength = (l / 8 + 1) * 8;
            l = desiredLength;
            message = padLast(message, desiredLength);
        }

        for (i = 0; i < l; i += 8) {
            String block = message.substring(i, i + 8);
            StringBuilder bitBlock = new StringBuilder();
            byte[] bytes = block.getBytes();
            for (j = 0; j < 8; j++) {
                bitBlock.append(pad(Integer.toBinaryString(bytes[j]), 8));
            }
            encryptedMessage.append(encryptBlock(bitBlock.toString(), subKeys));
        }
        return encryptedMessage.toString();
    }

    /**
     * @param message The encrypted string. Expects it to be a multiple of 64 bits, in binary format
     * @return The decrypted String, in plain English
     */
    public String decrypt(String message) {
        StringBuilder decryptedMessage = new StringBuilder();
        int l = message.length(), i, j;
        if (l % 64 != 0) {
            throw new IllegalArgumentException("Encrypted message should be a multiple of 64 characters in length");
        }
        for (i = 0; i < l; i += 64) {
            String block = message.substring(i, i + 64);
            String result = decryptBlock(block.toString(), subKeys);
            byte res[] = new byte[8];
            for (j = 0; j < 64; j += 8) {
                res[j / 8] = (byte) Integer.parseInt(result.substring(j, j + 8), 2);
            }
            decryptedMessage.append(new String(res));
        }
        return decryptedMessage.toString().replace("\0", ""); // Get rid of the null bytes used for padding
    }
}
