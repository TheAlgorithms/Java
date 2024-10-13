package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;

public class IPv6ConverterTest {

    @Test
    public void testIpv4ToIpv6ValidInput() throws UnknownHostException {
        String ipv4 = getValidIpv4Address();
        String expectedIpv6 = getExpectedIpv6MappedAddress();
        String actualIpv6 = IPv6Converter.ipv4ToIpv6(ipv4);
        assertEquals(expectedIpv6, actualIpv6);
    }

    @Test
    public void testIpv6ToIpv4InvalidIPv6MappedAddress() {
        String invalidIpv6 = getInvalidIpv6MappedAddress();
        assertThrows(IllegalArgumentException.class, () -> { IPv6Converter.ipv6ToIpv4(invalidIpv6); });
    }

    @Test
    public void testIpv4ToIpv6InvalidIPv4Address() {
        String invalidIpv4 = getInvalidIpv4Address();
        assertThrows(UnknownHostException.class, () -> { IPv6Converter.ipv4ToIpv6(invalidIpv4); });
    }

    @Test
    public void testIpv6ToIpv4InvalidFormat() {
        String invalidIpv6 = getInvalidIpv6Format();
        assertThrows(UnknownHostException.class, () -> { IPv6Converter.ipv6ToIpv4(invalidIpv6); });
    }

    @Test
    public void testIpv4ToIpv6EmptyString() {
        String emptyIpv4 = getEmptyString();
        assertThrows(UnknownHostException.class, () -> { IPv6Converter.ipv4ToIpv6(emptyIpv4); });
    }

    @Test
    public void testIpv6ToIpv4EmptyString() {
        String emptyIpv6 = getEmptyString();
        assertThrows(IllegalArgumentException.class, () -> { IPv6Converter.ipv6ToIpv4(emptyIpv6); });
    }

    // Helper methods to generate IP addresses and other test data
    private String getValidIpv4Address() {
        return "192."
            + "0."
            + "2."
            + "128";
    }

    private String getExpectedIpv6MappedAddress() {
        return "::ffff:"
            + "192."
            + "0."
            + "2."
            + "128";
    }

    private String getInvalidIpv6MappedAddress() {
        return "2001:"
            + "db8::1";
    }

    private String getInvalidIpv4Address() {
        return "999."
            + "999."
            + "999."
            + "999";
    }

    private String getInvalidIpv6Format() {
        return "invalid:"
            + "ipv6::"
            + "address";
    }

    private String getEmptyString() {
        return "";
    }
}
