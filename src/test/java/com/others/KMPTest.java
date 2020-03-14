package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class KMPTest {

    @Test
    void testKMP() {
        KMP kmp = new KMP();

        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(1);
        Assertions.assertEquals(result, kmp.KMPmatcher("AAAAABAAABA", "AAAA"), "Incorrect Conversion");

        ArrayList<Integer> result2 = new ArrayList<>();
        Assertions.assertEquals(result2, kmp.KMPmatcher("ABABABA", "AAAA"), "Incorrect Conversion");
    }
}
