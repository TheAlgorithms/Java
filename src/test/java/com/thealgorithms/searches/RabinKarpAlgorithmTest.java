package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RabinKarpAlgorithmTest {
  RabinKarpAlgorithm RKA = new RabinKarpAlgorithm();
  int q = 101;

  @ParameterizedTest
  @CsvSource(
    {
      "This is an example for rabin karp algorithmn, algorithmn",
      "AAABBDDG, AAA",
      "AAABBCCBB, BBCC",
      "AAAABBBBCCC, CCC",
      "ABCBCBCAAB, AADB"
    }
  )
  void RabinKarpAlgorithmTestExample(String txt, String pat) {
    int indexFromOurAlgorithm = RKA.search(pat, txt, q);
    int indexFromLinearSearch = txt.indexOf(pat);
    assertEquals(indexFromOurAlgorithm, indexFromLinearSearch);
  }
}
