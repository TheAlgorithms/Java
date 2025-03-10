package com.thealgorithms.dynamicprogramming;

/**
 * A DynamicProgramming based solution for Edit Distance problem In Java
 * Description of Edit Distance with an Example:
 *
 * <p>
 * Edit distance is a way of quantifying how dissimilar two strings (e.g.,
 * words) are to one another, by counting the minimum number of operations
 * required to transform one string into the other. The distance operations are
 * the removal, insertion, or substitution of a character in the string.
 *
 * <p>
 *
 * <p>
 * The Distance between "kitten" and "sitting" is 3. A minimal edit script that
 * transforms the former into the latter is:
 *
 * <p>
 * kitten → sitten (substitution of "s" for "k") sitten → sittin (substitution
 * of "i" for "e") sittin → sitting (insertion of "g" at the end).
 *
 * @author SUBHAM SANGHAI
 */
public final class EditDistance {
    private EditDistance() {
    }

    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // len1+1, len2+1, because finally return dp[len1][len2]
        int[][] dp = new int[len1 + 1][len2 + 1];
        /* If second string is empty, the only option is to
    insert all characters of first string into second*/
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        /* If first string is empty, the only option is to
    insert all characters of second string into first*/
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        // iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);
                // if last two chars equal
                if (c1 == c2) {
                    // update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    /* if two characters are different ,
          then take the minimum of the various operations(i.e insertion,removal,substitution)*/
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = Math.min(replace, insert);
                    min = Math.min(delete, min);
                    dp[i + 1][j + 1] = min;
                }
            }
        }
        /* return the final answer , after traversing through both the strings*/
        return dp[len1][len2];
    }

    // edit distance problem
    public static int editDistance(String s1, String s2) {
        int[][] storage = new int[s1.length() + 1][s2.length() + 1];
        return editDistance(s1, s2, storage);
    }

    public static int editDistance(String s1, String s2, int[][] storage) {
        int m = s1.length();
        int n = s2.length();
        if (storage[m][n] > 0) {
            return storage[m][n];
        }
        if (m == 0) {
            storage[m][n] = n;
            return storage[m][n];
        }
        if (n == 0) {
            storage[m][n] = m;
            return storage[m][n];
        }
        if (s1.charAt(0) == s2.charAt(0)) {
            storage[m][n] = editDistance(s1.substring(1), s2.substring(1), storage);
        } else {
            int op1 = editDistance(s1, s2.substring(1), storage);
            int op2 = editDistance(s1.substring(1), s2, storage);
            int op3 = editDistance(s1.substring(1), s2.substring(1), storage);
            storage[m][n] = 1 + Math.min(op1, Math.min(op2, op3));
        }
        return storage[m][n];
    }
}
