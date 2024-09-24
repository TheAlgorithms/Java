public class ManacherAlgorithm {

    // Preprocesses the input string by adding boundary characters
    private String preprocess(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('^');  // Add starting boundary
        for (int i = 0; i < s.length(); i++) {
            sb.append('#');  // Add boundary between characters
            sb.append(s.charAt(i));
        }
        sb.append("#$");  // Add ending boundary
        return sb.toString();
    }

    // Manacher's Algorithm to find the longest palindromic substring
    public String longestPalindrome(String s) {
        String T = preprocess(s);
        int n = T.length();
        int[] P = new int[n];  // Array to store the lengths of palindromes
        int C = 0, R = 0;  // C is the center, R is the right boundary

        // Iterate through the transformed string
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * C - i;  // Mirror of i with respect to the center C

            // Use previously computed values or set P[i] to 0
            P[i] = (R > i) ? Math.min(R - i, P[mirror]) : 0;

            // Expand around i
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            // Update center and right boundary if the palindrome expands beyond R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }

        // Find the longest palindrome in P
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        // Calculate the starting index of the longest palindromic substring
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }
}

