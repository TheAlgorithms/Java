package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

public class LongestCommonSubsequenceTest {

    /**
     * Test if two input strings are different
     */
    @Test
    void twoDifferentStrings(){
        String str1 = "ABCD";
        String str2 = "ABDC";
        String expectedLCS = "ABD";
        String actualLCS = LongestCommonSubsequence.getLCS(str1, str2);
        // assert that a string is returned, which is comprised exclusively 
        // of characters shared by the two input strings.
        assertEquals(expectedLCS, actualLCS);
    }

    /**
     * Test if two input strings are the same
     */
    @Test
    void twoIdenticalStrings(){
        String str1 = "ABD";
        String str2 = "ABD";
        String expectedLCS = "ABD";
        String actualLCS = LongestCommonSubsequence.getLCS(str1, str2);
        // assert that a string is returned, which is comprised exclusively 
        // of characters shared by the two input strings.
        assertEquals(expectedLCS, actualLCS);
    }

    /**
     * Test if two input strings share no common characters 
     */
    @Test
    void noSharedCharacters(){
        String str1 = "ABC";
        String str2 = "DEF";
        String expectedLCS = "";
        String actualLCS = LongestCommonSubsequence.getLCS(str1, str2);
        // assert that an empty string is returned, given the two input
        // strings share no common characters.
        assertEquals(expectedLCS, actualLCS);
    }

    /**
     * Test if one input arg (which should be a string), is null
     */
    @Test
    void oneNullString(){
        String str1 = null;
        String str2 = "ABDC";
        String expectedLCS = null;
        String actualLCS = LongestCommonSubsequence.getLCS(str1, str2);
        // assert that null is returned, given that one of the input 
        // strings is null. 
        assertEquals(expectedLCS, actualLCS);

    }

    /**
     * Test if one string is empty
     */
    @Test
    void oneEmptyString(){
        String str1 = "";
        String str2 = "ABDC";
        String expectedLCS = "";
        String actualLCS = LongestCommonSubsequence.getLCS(str1, str2);
        // assert that an empty string is returned, given that one of the 
        // input strings is empty.
        assertEquals(expectedLCS, actualLCS);

    }

}

