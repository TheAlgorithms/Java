package com.thealgorithms.conversions;

/**
 * Converts an IPv4 address to its binary equivalent and vice-versa.
 * IP to Binary: Converts an IPv4 address to its binary equivalent.
 * Example: 127.3.4.5 -> 01111111.00000011.00000100.00000101
 *
 * Binary to IP: Converts a binary equivalent to an IPv4 address.
 * Example: 01111111.00000011.00000100.00000101 -> 127.3.4.5
 *
 * @author Hardvan
 */
public final class IPConverter {
    private IPConverter() {
    }

    /**
     * Converts an IPv4 address to its binary equivalent.
     * @param ip The IPv4 address to convert.
     * @return The binary equivalent of the IPv4 address.
     */
    public static String ipToBinary(String ip) {
        StringBuilder binary = new StringBuilder();
        for (String octet : ip.split("\\.")) {
            binary.append(octetToBinary(Integer.parseInt(octet))).append(".");
        }
        return binary.substring(0, binary.length() - 1);
    }

    /**
     * Converts a single octet to its 8-bit binary representation.
     * @param octet The octet to convert (0-255).
     * @return The 8-bit binary representation as a String.
     */
    private static String octetToBinary(int octet) {
        char[] binary = {'0', '0', '0', '0', '0', '0', '0', '0'};
        for (int i = 7; i >= 0; i--) {
            if ((octet & 1) == 1) {
                binary[i] = '1';
            }
            octet >>>= 1;
        }
        return new String(binary);
    }

    /**
     * Converts a binary equivalent to an IPv4 address.
     * @param binary The binary equivalent to convert.
     * @return The IPv4 address of the binary equivalent.
     */
    public static String binaryToIP(String binary) {
        StringBuilder ip = new StringBuilder();
        for (String octet : binary.split("\\.")) {
            ip.append(Integer.parseInt(octet, 2)).append(".");
        }
        return ip.substring(0, ip.length() - 1);
    }
}
