package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test case for Remove All Adjacent Duplicates In String
 * @author Akshit Kumar Chandora (https://github.com/axitchandora)
 */

public class RemoveAllAdjacentDuplicatesInStringTest {

  @Test
  void testRemoveDuplicates() {
    assertEquals(
        "", RemoveAllAdjacentDuplicatesInString.removeDuplicates("abccba"));
    assertEquals(
        "fbar", RemoveAllAdjacentDuplicatesInString.removeDuplicates("foobar"));
    assertEquals("abcd",
                 RemoveAllAdjacentDuplicatesInString.removeDuplicates("abcd"));
  }
}
