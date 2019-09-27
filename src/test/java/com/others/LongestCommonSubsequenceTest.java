package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LongestCommonSubsequenceTest {
    @Test
    void TestLCS() {
        int[][] matrix = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = 1;
            }
        }
        Assertions.assertEquals("", LongestCommonSubsequence.lcsString("", "abc", matrix));
//            Assertions.assert("",LongestCommonSubsequence.lcsString( "abc", "abc", matrix));
        Assertions.assertEquals("", LongestCommonSubsequence.lcsString("abc", "", matrix));
        Assertions.assertEquals("", LongestCommonSubsequence.lcsString("acbd", "", matrix));
    }

}
