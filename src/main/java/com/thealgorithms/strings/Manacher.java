public class ManacherAlgorithm {
    private String preprocess(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('^');
        for (int i = 0; i < s.length(); i++) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append("#$");
        return sb.toString();
    }
    public String longestPalindrome(String s) {
        String T = preprocess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0; 
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * C - i; 
            P[i] = (R > i) ? Math.min(R - i, P[mirror]) : 0;
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
      }
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        ManacherAlgorithm ma = new ManacherAlgorithm();
        String input = "babad";
        System.out.println("Longest Palindromic Substring: " + ma.longestPalindrome(input));
    }
}
