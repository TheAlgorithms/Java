package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class LyndonFactorizationTest {

    @Test
    void factorizesBanana() {
        assertEquals(List.of("b", "an", "an", "a"), LyndonFactorization.factorize("banana"));
    }

    @Test
    void factorizesRepeatedPattern() {
        assertEquals(List.of("ab", "ab", "ab"), LyndonFactorization.factorize("ababab"));
    }

    @Test
    void factorizesSingleCharacter() {
        assertEquals(List.of("a"), LyndonFactorization.factorize("a"));
    }

    @Test
    void factorizesEmptyString() {
        assertEquals(List.of(), LyndonFactorization.factorize(""));
    }

    @Test
    void rejectsNullInput() {
        assertThrows(IllegalArgumentException.class, () -> LyndonFactorization.factorize(null));
    }
}
