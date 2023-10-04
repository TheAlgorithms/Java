package com.thealgorithms.strings;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class longestcommonprefixtest {
    @Test
    public void longestCommonPrefixTest() {
        String[] input1 = {"flower", "flow", "float"};
        String[] input2 = {"build", "built", "builder"};
        String[] input3 = {"cat", "rat"};
        String[] input4 = {"dog","racecar","car"};
        
        String expectedOutput1 = "flo";
        String expectedOutput2 = "buil";
        String expectedOutput3 = "";
        String expectedOutput4 = "";

        assertEquals(LongestCommonPrefix.findLongestCommonPrefix(input1), expectedOutput1);
        assertEquals(LongestCommonPrefix.findLongestCommonPrefix(input2), expectedOutput2);
        assertEquals(LongestCommonPrefix.findLongestCommonPrefix(input3), expectedOutput3);
        assertEquals(LongestCommonPrefix.findLongestCommonPrefix(input4), expectedOutput4);
        
    }
}
