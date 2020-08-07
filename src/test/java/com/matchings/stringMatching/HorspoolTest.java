package com.matchings.stringMatching;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HorspoolTest {

  @Test
  void testFirstOccurrence() {
    String text = "BESS_KNEW_ABOUT_BAOBABS";

    String patternThatExists = "BAOBAB";
    Integer expectedIndex = 16;
    assertEquals(expectedIndex, Horspool.findFirstOccurrence(patternThatExists, text));

    String patterThatDoesNotExist = "BESSY";
    assertNull(Horspool.findFirstOccurrence(patterThatDoesNotExist, text));
  }

  @Test
  void testFirstExactOccurrence() {
    String text = "Manual refactoring is not recommended";

    String patterThatExists = "ring is not";
    Integer expectedIndex = 14;
    assertEquals(expectedIndex, Horspool.findFirstExactOccurrence(patterThatExists, text));

    String patternThatDoesNotExist = "man";
    assertNull(Horspool.findFirstExactOccurrence(patternThatDoesNotExist, text));
  }

  /**
   * Test number of character comparisons in searching for: AB, ABB, ABBB, ABBBB in 1000 As
   */
  @Test
  void testComparisons() {
    final int N = 1000;

    StringBuilder builder = new StringBuilder(N);
    for (int i=0; i<N; i++) {
      builder.append('A');
    }

    String text = builder.toString();
    String[] patterns = { "AB", "ABB", "ABBB", "ABBBB" };

    for (String pattern : patterns) {
      assertNull(Horspool.findFirstOccurrence(pattern, text));
      Long expected = Math.round(Math.ceil(
          (double) (text.length() - pattern.length() + 1) / (pattern.length() - 1))); // 999, 499, 333, 249
      assertEquals(expected, (long) Horspool.getLastComparisons());
    }
  }

}
