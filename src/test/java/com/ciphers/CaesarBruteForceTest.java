package com.ciphers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CaesarBruteForceTest {
    @Test
    void testCaesarBruteForce() {
        CaesarBruteForce cipher = new CaesarBruteForce();

        Assertions.assertSame(true, cipher.decrypt("TKFK", 1).contains("JAVA"));
        Assertions.assertSame(true, cipher.decrypt("QHCH", 1).contains("JAVA"));
        Assertions.assertSame(true, cipher.decrypt("LUHREIU", 1).contains("VERBOSE"));
        Assertions.assertSame(true, cipher.decrypt("Mkockb", 1).contains("CAESAR"));
        Assertions.assertSame(true, cipher.decrypt("olssv", 1).contains("HELLO"));
        Assertions.assertSame(true, cipher.decrypt("Zvksxdohd", 1).contains("PLAINTEXT"));
        Assertions.assertSame(true, cipher.decrypt("XGVKRIM", 1).contains("ENCRYPT"));
        Assertions.assertSame(true, cipher.decrypt("OZRRVNQC123", 1).contains("PASSWORD123"));
        Assertions.assertSame(true, cipher.decrypt("GEQDZMYQ", 1).contains("USERNAME"));
        Assertions.assertSame(true, cipher.decrypt("IKHMXVMXW", 1).contains("PROTECTED"));
        Assertions.assertSame(true, cipher.decrypt("ZPSRC-DMPACB", 1).contains("BRUTE-FORCED"));
        Assertions.assertSame(true, cipher.decrypt("VCTKJ!", 1).contains("PWNED!"));
        Assertions.assertSame(true, cipher.decrypt("JKLMNOP", 1).contains("ABCDEFG"));
        Assertions.assertSame(true, cipher.decrypt("QFMDHCUFODVWQ", 1).contains("CRYPTOGRAPHIC"));
        Assertions.assertSame(true, cipher.decrypt("Dmbncdc", 1).contains("ENCODED"));
    }
}

