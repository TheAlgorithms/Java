package com.thealgorithms.strings;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.StringUtils;


/**
 * @author Imran Ansari
 * @note This class is free from sonar lint issues
 */
class LongestCommonPrefixTest {

    @org.junit.jupiter.api.Test
    void testGetLongestCommonPrefix() {
        String[] strs = {"flower", "flow", "flight", "flair", "flag"};
        assertEquals("fl", LongestCommonPrefix.getLongestCommonPrefix(strs));
    }

    @org.junit.jupiter.api.Test
    void testGetLongestCommonPrefixWithOneInvalidString() {
        String[] strs = {"flower", "flow", "flight", "flair", "random"};
        assertEquals(StringUtils.EMPTY, LongestCommonPrefix.getLongestCommonPrefix(strs));
    }

    @org.junit.jupiter.api.Test
    void testGetLongestCommonPrefixRandomString() {
        String[] strs = {"this", "is", "to", "test", "randomly"};
        assertEquals(StringUtils.EMPTY, LongestCommonPrefix.getLongestCommonPrefix(strs));
    }
}
