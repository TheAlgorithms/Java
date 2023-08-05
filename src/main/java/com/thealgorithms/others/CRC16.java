package com.thealgorithms.others;

/**
 * Generates a crc16 checksum for a given string
 */
public class CRC16 {

    public static void main(String[] args) {
        System.out.println(crc16("Hello World!"));
    }

    public static String crc16(String message) {
        int crc = 0xFFFF; // initial value
        int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)
        byte[] bytes = message.getBytes();

        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
            }
        }
        crc &= 0xffff;
        return Integer.toHexString(crc).toUpperCase();
    }
}
