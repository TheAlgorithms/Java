package DynamicProgramming;

/**
 * @author Dipyaman Saha (https://github.com/dipyamansaha)
 * - LONGEST COMMON SUBSTRING PROBLEM.
 * - Given two strings ‘x’ and ‘y’, we find the length of the longest common substring.
 * - https://en.wikipedia.org/wiki/Longest_common_substring_problem
 */

public class LongestCommonSubstring {
    static int lcSubstring(String x, String y) {
        int m = x.length();
        int n = y.length();

        int[][] t = new int[m + 1][n + 1];
        for (int i = 0; i < (m + 1); i++)
            for (int j = 0; j < (n + 1); j++)
                if (i == 0 || j == 0)
                    t[i][j] = 0;

        int result = 0;
        for (int i = 1; i < (m + 1); i++)
            for (int j = 1; j < (n + 1); j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1))
                    t[i][j] = 1 + t[i - 1][j - 1];
                else
                    t[i][j] = 0;

                result = Math.max(result, t[i][j]);
            }

        return result;
    }

    public static void main(String[] args) {
        String x = "abcde";
        String y = "abfce";

        System.out.println("Length of the Longest Common Substring: " + lcSubstring(x, y));
    }
}
