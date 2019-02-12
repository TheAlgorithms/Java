package src.test.java.com.crypto.codec;

import src.main.java.com.crypto.codec.Base64;
import org.junit.Test;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class Base64Test {

    /*
     * Test vectors are taken from:
     * https://tools.ietf.org/html/rfc4648#section-10
     */

    @Test
    public void TestBase64Encode() {
        assertEquals("", Base64.encode("".getBytes()));
        assertEquals("Zg==", Base64.encode("f".getBytes()));
        assertEquals("Zm8=", Base64.encode("fo".getBytes()));
        assertEquals("Zm9v", Base64.encode("foo".getBytes()));
        assertEquals("Zm9vYg==", Base64.encode("foob".getBytes()));
        assertEquals("Zm9vYmE=", Base64.encode("fooba".getBytes()));
        assertEquals("Zm9vYmFy", Base64.encode("foobar".getBytes()));
    }

    @Test
    public void TestBase64Decode() {
        assertArrayEquals("".getBytes(), Base64.decode(""));
        assertArrayEquals("f".getBytes(), Base64.decode("Zg=="));
        assertArrayEquals("fo".getBytes(), Base64.decode("Zm8="));
        assertArrayEquals("foo".getBytes(), Base64.decode("Zm9v"));
        assertArrayEquals("foob".getBytes(), Base64.decode("Zm9vYg=="));
        assertArrayEquals("fooba".getBytes(), Base64.decode("Zm9vYmE="));
        assertArrayEquals("foobar".getBytes(), Base64.decode("Zm9vYmFy"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBase64String() {
        Base64.decode("Z/+v&mF=");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLengthOfBase64String() {
        Base64.decode("Zm9v" + "YmFy" + "d");
    }
}
