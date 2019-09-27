package com.ciphers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Nguyen Giang
 */
public class VigenereTest {

    @Test
    void testEncript() {
        Assertions.assertEquals("LXFOPVEFRNHR", Vigenere.encrypt("ATTACKATDAWN", "LEMON"));
        Assertions.assertEquals("3dvqv", Vigenere.encrypt("3home", "where"));
        Assertions.assertEquals("kYmLbrze", Vigenere.encrypt("yUoRname", "me"));
        Assertions.assertEquals("33333", Vigenere.encrypt("33333", "you"));
        Assertions.assertEquals("&*qqbgqs", Vigenere.encrypt("&*school", "you"));
    }
}