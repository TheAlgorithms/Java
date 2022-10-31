package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FirstUniqueCharacterTest {
	
    @Test
    void testOne() {
        assertEquals(0, FirstUniqueCharacter.firstUniqChar("leetcode"));
    }
  
      @Test
    void testTwo() {
        assertEquals(2, FirstUniqueCharacter.firstUniqChar("loveleetcode"));
    }
  
  
      @Test
    void testThree() {
        assertEquals(-1, FirstUniqueCharacter.firstUniqChar("aabb"));
    }
	
}
