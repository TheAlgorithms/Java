package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.Test;

public class IsomorphicTest {

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

        Isomorphic isomorphic = new Isomorphic();

        assertTrue(isomorphic.checkStrings(str1, str2));
        assertTrue(isomorphic.checkStrings(str3, str4));
        assertFalse(isomorphic.checkStrings(str5, str6));
        assertFalse(isomorphic.checkStrings(str7, str8));
    }
}
