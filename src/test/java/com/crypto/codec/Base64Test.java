package com.crypto.codec;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Base64Test {

    /*
     * Test vectors are taken from:
     * https://tools.ietf.org/html/rfc4648#section-10
     */

    @Test
    void TestBase64Encode() {
        Assertions.assertEquals("", Base64.encode("".getBytes()));
        Assertions.assertEquals("Zg==", Base64.encode("f".getBytes()));
        Assertions.assertEquals("Zm8=", Base64.encode("fo".getBytes()));
        Assertions.assertEquals("Zm9v", Base64.encode("foo".getBytes()));
        Assertions.assertEquals("Zm9vYg==", Base64.encode("foob".getBytes()));
        Assertions.assertEquals("Zm9vYmE=", Base64.encode("fooba".getBytes()));
        Assertions.assertEquals("Zm9vYmFy", Base64.encode("foobar".getBytes()));
    }

    @Test
    void TestBase64Decode() {
        Assertions.assertArrayEquals("".getBytes(), Base64.decode(""));
        Assertions.assertArrayEquals("f".getBytes(), Base64.decode("Zg=="));
        Assertions.assertArrayEquals("fo".getBytes(), Base64.decode("Zm8="));
        Assertions.assertArrayEquals("foo".getBytes(), Base64.decode("Zm9v"));
        Assertions.assertArrayEquals("foob".getBytes(), Base64.decode("Zm9vYg=="));
        Assertions.assertArrayEquals("fooba".getBytes(), Base64.decode("Zm9vYmE="));
        Assertions.assertArrayEquals("foobar".getBytes(), Base64.decode("Zm9vYmFy"));
    }

    @Test
    void testInvalidBase64String() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Base64.decode("Z/+v&mF="));
    }

    @Test
    void testInvalidLengthOfBase64String() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Base64.decode("Zm9v" + "YmFy" + "d"));
    }
}
