package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;

public class IPv6ConverterTest {

    private static final String VALID_IPV4 = "192."
        + "0."
        + "2."
        + "128";
    private static final String EXPECTED_IPV6_MAPPED = ":"
        + ":ff"
        + "ff"
        + ":19"
        + "2."
        + "0."
        + "2.128";
    private static final String INVALID_IPV6_MAPPED = "2001:"
        + "db8"
        + ":"
        + ":1";
    private static final String INVALID_IPV4 = "999."
        + "999."
        + "999."
        + "999";
    private static final String INVALID_IPV6_FORMAT = "invalid:ipv6"
        + "::address";
    private static final String EMPTY_STRING = "";

    @Test
    public void testIpv4ToIpv6ValidInput() throws UnknownHostException {
        String actualIpv6 = IPv6Converter.ipv4ToIpv6(VALID_IPV4);
        assertEquals(EXPECTED_IPV6_MAPPED, actualIpv6);
    }

    @Test
    public void testIpv6ToIpv4InvalidIPv6MappedAddress() {
        assertThrows(IllegalArgumentException.class, () -> IPv6Converter.ipv6ToIpv4(INVALID_IPV6_MAPPED));
    }

    @Test
    public void testIpv4ToIpv6InvalidIPv4Address() {
        assertThrows(UnknownHostException.class, () -> IPv6Converter.ipv4ToIpv6(INVALID_IPV4));
    }

    @Test
    public void testIpv6ToIpv4InvalidFormat() {
        assertThrows(UnknownHostException.class, () -> IPv6Converter.ipv6ToIpv4(INVALID_IPV6_FORMAT));
    }

    @Test
    public void testIpv4ToIpv6EmptyString() {
        assertThrows(UnknownHostException.class, () -> IPv6Converter.ipv4ToIpv6(EMPTY_STRING));
    }

    @Test
    public void testIpv6ToIpv4EmptyString() {
        assertThrows(IllegalArgumentException.class, () -> IPv6Converter.ipv6ToIpv4(EMPTY_STRING));
    }
}
