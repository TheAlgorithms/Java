package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

public class IPv6ConverterTest {

    @Test
    public void testIpv4ToIpv6_ValidInput() throws UnknownHostException {
        String ipv4 = "192.0.2.128";
        String expectedIpv6 = "::ffff:192.0.2.128";
        String actualIpv6 = IPv6Converter.ipv4ToIpv6(ipv4);
        assertEquals(expectedIpv6, actualIpv6);
    }

    @Test
    public void testIpv6ToIpv4_InvalidIPv6MappedAddress() {
        String invalidIpv6 = "2001:db8::1"; // Not an IPv6-mapped IPv4
        assertThrows(IllegalArgumentException.class, () -> {
            IPv6Converter.ipv6ToIpv4(invalidIpv6);
        });
    }

    @Test
    public void testIpv4ToIpv6_InvalidIPv4Address() {
        String invalidIpv4 = "999.999.999.999"; // Invalid IPv4 address
        assertThrows(UnknownHostException.class, () -> {
            IPv6Converter.ipv4ToIpv6(invalidIpv4);
        });
    }

    @Test
    public void testIpv6ToIpv4_InvalidFormat() {
        String invalidIpv6 = "invalid:ipv6::address";
        assertThrows(UnknownHostException.class, () -> {
            IPv6Converter.ipv6ToIpv4(invalidIpv6);
        });
    }

    @Test
    public void testIpv4ToIpv6_EmptyString() {
        String emptyIpv4 = "";
        assertThrows(UnknownHostException.class, () -> {
            IPv6Converter.ipv4ToIpv6(emptyIpv4);
        });
    }

    @Test
    public void testIpv6ToIpv4_EmptyString() {
        String emptyIpv6 = "";
        assertThrows(IllegalArgumentException.class, () -> {
            IPv6Converter.ipv6ToIpv4(emptyIpv6);
        });
    }
}
