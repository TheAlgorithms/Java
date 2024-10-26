package com.thealgorithms.conversions;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * A utility class for converting between IPv6 and IPv4 addresses.
 *
 * - Converts IPv4 to IPv6-mapped IPv6 address.
 * - Extracts IPv4 address from IPv6-mapped IPv6.
 * - Handles exceptions for invalid inputs.
 *
 * @author Hardvan
 */
public final class IPv6Converter {
    private IPv6Converter() {
    }

    /**
     * Converts an IPv4 address (e.g., "192.0.2.128") to an IPv6-mapped IPv6 address.
     * Example: IPv4 "192.0.2.128" -> IPv6 "::ffff:192.0.2.128"
     *
     * @param ipv4Address The IPv4 address in string format.
     * @return The corresponding IPv6-mapped IPv6 address.
     * @throws UnknownHostException If the IPv4 address is invalid.
     * @throws IllegalArgumentException If the IPv6 address is not a mapped IPv4 address.
     */
    public static String ipv4ToIpv6(String ipv4Address) throws UnknownHostException {
        if (ipv4Address == null || ipv4Address.isEmpty()) {
            throw new UnknownHostException("IPv4 address is empty.");
        }

        InetAddress ipv4 = InetAddress.getByName(ipv4Address);
        byte[] ipv4Bytes = ipv4.getAddress();

        // Create IPv6-mapped IPv6 address (starts with ::ffff:)
        byte[] ipv6Bytes = new byte[16];
        ipv6Bytes[10] = (byte) 0xff;
        ipv6Bytes[11] = (byte) 0xff;
        System.arraycopy(ipv4Bytes, 0, ipv6Bytes, 12, 4);

        // Manually format to "::ffff:x.x.x.x" format
        StringBuilder ipv6String = new StringBuilder("::ffff:");
        for (int i = 12; i < 16; i++) {
            ipv6String.append(ipv6Bytes[i] & 0xFF);
            if (i < 15) {
                ipv6String.append('.');
            }
        }
        return ipv6String.toString();
    }

    /**
     * Extracts the IPv4 address from an IPv6-mapped IPv6 address.
     * Example: IPv6 "::ffff:192.0.2.128" -> IPv4 "192.0.2.128"
     *
     * @param ipv6Address The IPv6 address in string format.
     * @return The extracted IPv4 address.
     * @throws UnknownHostException If the IPv6 address is invalid or not a mapped IPv4 address.
     */
    public static String ipv6ToIpv4(String ipv6Address) throws UnknownHostException {
        InetAddress ipv6 = InetAddress.getByName(ipv6Address);
        byte[] ipv6Bytes = ipv6.getAddress();

        // Check if the address is an IPv6-mapped IPv4 address
        if (isValidIpv6MappedIpv4(ipv6Bytes)) {
            byte[] ipv4Bytes = Arrays.copyOfRange(ipv6Bytes, 12, 16);
            InetAddress ipv4 = InetAddress.getByAddress(ipv4Bytes);
            return ipv4.getHostAddress();
        } else {
            throw new IllegalArgumentException("Not a valid IPv6-mapped IPv4 address.");
        }
    }

    /**
     * Helper function to check if the given byte array represents
     * an IPv6-mapped IPv4 address (prefix 0:0:0:0:0:ffff).
     *
     * @param ipv6Bytes Byte array representation of the IPv6 address.
     * @return True if the address is IPv6-mapped IPv4, otherwise false.
     */
    private static boolean isValidIpv6MappedIpv4(byte[] ipv6Bytes) {
        // IPv6-mapped IPv4 addresses are 16 bytes long, with the first 10 bytes set to 0,
        // followed by 0xff, 0xff, and the last 4 bytes representing the IPv4 address.
        if (ipv6Bytes.length != 16) {
            return false;
        }

        for (int i = 0; i < 10; i++) {
            if (ipv6Bytes[i] != 0) {
                return false;
            }
        }

        return ipv6Bytes[10] == (byte) 0xff && ipv6Bytes[11] == (byte) 0xff;
    }
}
