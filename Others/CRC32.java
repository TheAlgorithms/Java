package Others;

import java.util.BitSet;

/**
 * Generates a crc32 checksum for a given string or byte array
 */
public class CRC32 {

    public static void main(String[] args) {
        System.out.println(Integer.toHexString(crc32("Hello World")));
    }

    public static int crc32(String str) {
        return crc32(str.getBytes());
    }

    public static int crc32(byte[] data) {
        BitSet bitSet = BitSet.valueOf(data);
        int crc32 = 0xFFFFFFFF; // initial value
        for (int i = 0; i < data.length * 8; i++) {
            if (((crc32 >>> 31) & 1) != (bitSet.get(i) ? 1 : 0))
                crc32 = (crc32 << 1) ^ 0x04C11DB7; // xor with polynomial
            else
                crc32 = (crc32 << 1);
        }
        crc32 = Integer.reverse(crc32); // result reflect
        return crc32 ^ 0xFFFFFFFF; // final xor value
    }

}
