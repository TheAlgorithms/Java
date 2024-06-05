package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public final class IsomorphicTest {
    private IsomorphicTest() {
    }

    @Test
    public static void main(String[] args) {
        String str1 = "abbbbaac";
        String str2 = "kffffkkd";

        String str3 = "xyxyxy";
        String str4 = "bnbnbn";

        String str5 = "ghjknnmm";
        String str6 = "wertpopo";

        String str7 = "aaammmnnn";
        String str8 = "ggghhhbbj";

        assertTrue(Isomorphic.checkStrings(str1, str2));
        assertTrue(Isomorphic.checkStrings(str3, str4));
        assertFalse(Isomorphic.checkStrings(str5, str6));
        assertFalse(Isomorphic.checkStrings(str7, str8));
    }
}
