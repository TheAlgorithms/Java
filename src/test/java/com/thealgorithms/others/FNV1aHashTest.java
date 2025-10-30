package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FNV1aHashTest {

    @Test
    void testHash32EmptyString() {
        // Empty string should return the FNV offset basis
        assertEquals(0x811c9dc5, FNV1aHash.hash32(""));
    }

    @Test
    void testHash32SingleCharacter() {
        assertEquals(0xe40c292c, FNV1aHash.hash32("a"));
        assertEquals(0xe70c2de5, FNV1aHash.hash32("b"));
        assertEquals(0xe60c2c52, FNV1aHash.hash32("c"));
    }

    @Test
    void testHash32SimpleStrings() {
        assertEquals(0x4ed566da, FNV1aHash.hash32("Hello"));
        assertEquals(0xdcfc127b, FNV1aHash.hash32("World"));
        assertEquals(0xc0bb652b, FNV1aHash.hash32("Algorithms"));
    }

    @Test
    void testHash32LongString() {
        assertEquals(0x048fff90, FNV1aHash.hash32("The quick brown fox jumps over the lazy dog"));
    }

    @Test
    void testHash32Consistency() {
        // Same input should always produce same output
        String input = "test";
        int hash1 = FNV1aHash.hash32(input);
        int hash2 = FNV1aHash.hash32(input);
        assertEquals(hash1, hash2);
    }

    @Test
    void testHash32Differentiation() {
        // Different inputs should produce different outputs
        int hash1 = FNV1aHash.hash32("test");
        int hash2 = FNV1aHash.hash32("Test");
        assertNotEquals(hash1, hash2);
    }

    @Test
    void testHash32WithByteArray() {
        byte[] data = "Hello".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        assertEquals(0x4ed566da, FNV1aHash.hash32(data));
    }

    @Test
    void testHash32WithEmptyByteArray() {
        byte[] data = new byte[0];
        assertEquals(0x811c9dc5, FNV1aHash.hash32(data));
    }

    @Test
    void testHash32NullStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> FNV1aHash.hash32((String) null));
    }

    @Test
    void testHash32NullByteArrayThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> FNV1aHash.hash32((byte[]) null));
    }

    @Test
    void testHash64EmptyString() {
        // Empty string should return the FNV offset basis
        assertEquals(0xcbf29ce484222325L, FNV1aHash.hash64(""));
    }

    @Test
    void testHash64SingleCharacter() {
        assertEquals(0xaf63dc4c8601ec8cL, FNV1aHash.hash64("a"));
        assertEquals(0xaf63df4c8601f1a5L, FNV1aHash.hash64("b"));
        assertEquals(0xaf63de4c8601f012L, FNV1aHash.hash64("c"));
    }

    @Test
    void testHash64SimpleStrings() {
        assertEquals(0x63f4e1f2c97e89ebL, FNV1aHash.hash64("Hello"));
        assertEquals(0x0f18f77b44424a53L, FNV1aHash.hash64("World"));
        assertEquals(0x289a4c6f7f076f3bL, FNV1aHash.hash64("Algorithms"));
    }

    @Test
    void testHash64LongString() {
        assertEquals(0xf3f9b7f5e7e47110L, FNV1aHash.hash64("The quick brown fox jumps over the lazy dog"));
    }

    @Test
    void testHash64Consistency() {
        // Same input should always produce same output
        String input = "test";
        long hash1 = FNV1aHash.hash64(input);
        long hash2 = FNV1aHash.hash64(input);
        assertEquals(hash1, hash2);
    }

    @Test
    void testHash64Differentiation() {
        // Different inputs should produce different outputs
        long hash1 = FNV1aHash.hash64("test");
        long hash2 = FNV1aHash.hash64("Test");
        assertNotEquals(hash1, hash2);
    }

    @Test
    void testHash64WithByteArray() {
        byte[] data = "Hello".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        assertEquals(0x63f4e1f2c97e89ebL, FNV1aHash.hash64(data));
    }

    @Test
    void testHash64WithEmptyByteArray() {
        byte[] data = new byte[0];
        assertEquals(0xcbf29ce484222325L, FNV1aHash.hash64(data));
    }

    @Test
    void testHash64NullStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> FNV1aHash.hash64((String) null));
    }

    @Test
    void testHash64NullByteArrayThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> FNV1aHash.hash64((byte[]) null));
    }

    @Test
    void testHash32HexFormat() {
        assertEquals("4ed566da", FNV1aHash.hash32Hex("Hello"));
        assertEquals("dcfc127b", FNV1aHash.hash32Hex("World"));
        assertEquals("811c9dc5", FNV1aHash.hash32Hex("")); // Empty string
    }

    @Test
    void testHash64HexFormat() {
        assertEquals("63f4e1f2c97e89eb", FNV1aHash.hash64Hex("Hello"));
        assertEquals("0f18f77b44424a53", FNV1aHash.hash64Hex("World"));
        assertEquals("cbf29ce484222325", FNV1aHash.hash64Hex("")); // Empty string
    }

    @Test
    void testHash32HexNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> FNV1aHash.hash32Hex(null));
    }

    @Test
    void testHash64HexNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> FNV1aHash.hash64Hex(null));
    }

    @Test
    void testHash32WithUnicodeCharacters() {
        // Test with Unicode characters
        assertDoesNotThrow(() -> FNV1aHash.hash32("Hello 世界"));
        assertDoesNotThrow(() -> FNV1aHash.hash32("🚀 Rocket"));
        
        // Different Unicode strings should have different hashes
        assertNotEquals(FNV1aHash.hash32("Hello"), FNV1aHash.hash32("Hello 世界"));
    }

    @Test
    void testHash64WithUnicodeCharacters() {
        // Test with Unicode characters
        assertDoesNotThrow(() -> FNV1aHash.hash64("Hello 世界"));
        assertDoesNotThrow(() -> FNV1aHash.hash64("🚀 Rocket"));
        
        // Different Unicode strings should have different hashes
        assertNotEquals(FNV1aHash.hash64("Hello"), FNV1aHash.hash64("Hello 世界"));
    }

    @Test
    void testHash32Distribution() {
        // Test that similar strings produce different hashes
        int hash1 = FNV1aHash.hash32("algorithm");
        int hash2 = FNV1aHash.hash32("algorithms");
        int hash3 = FNV1aHash.hash32("algorithmz");
        
        assertNotEquals(hash1, hash2);
        assertNotEquals(hash2, hash3);
        assertNotEquals(hash1, hash3);
    }

    @Test
    void testHash64Distribution() {
        // Test that similar strings produce different hashes
        long hash1 = FNV1aHash.hash64("algorithm");
        long hash2 = FNV1aHash.hash64("algorithms");
        long hash3 = FNV1aHash.hash64("algorithmz");
        
        assertNotEquals(hash1, hash2);
        assertNotEquals(hash2, hash3);
        assertNotEquals(hash1, hash3);
    }

    @Test
    void testHash32WithNumericStrings() {
        assertNotEquals(FNV1aHash.hash32("123"), FNV1aHash.hash32("1234"));
        assertNotEquals(FNV1aHash.hash32("999"), FNV1aHash.hash32("1000"));
    }

    @Test
    void testHash64WithNumericStrings() {
        assertNotEquals(FNV1aHash.hash64("123"), FNV1aHash.hash64("1234"));
        assertNotEquals(FNV1aHash.hash64("999"), FNV1aHash.hash64("1000"));
    }
}
