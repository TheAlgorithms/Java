package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FirstUniqueCharacterTest {
	
    @Test
    void testOne() {
        assertEquals(0, FirstUniqueCharacterTest.firstUniqChar("leetcode"));
    }
  
      @Test
    void testTwo() {
        assertEquals(2, FirstUniqueCharacterTest.firstUniqChar("loveleetcode"));
    }
  
  
      @Test
    void testThree() {
        assertEquals(-1, FirstUniqueCharacterTest.firstUniqChar("aabb"));
    }
	
}
