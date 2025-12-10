package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RabinKarpAlgorithmTest {

    @ParameterizedTest
    @CsvSource({"This is an example for rabin karp algorithmn, algorithmn, 101", "AAABBDDG, AAA, 137", "AAABBCCBB, BBCC, 101", "AAABBCCBB, BBCC, 131", "AAAABBBBCCC, CCC, 41", "ABCBCBCAAB, AADB, 293", "Algorithm The Algorithm, Algorithm, 101"})
    void rabinKarpAlgorithmTestExample(String txt, String pat, int q) {
        int indexFromOurAlgorithm = RabinKarpAlgorithm.search(pat, txt, q);
        int indexFromLinearSearch = txt.indexOf(pat);
        assertEquals(indexFromOurAlgorithm, indexFromLinearSearch);
    }
}
