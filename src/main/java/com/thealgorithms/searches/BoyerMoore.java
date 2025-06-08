/**
 * Boyer-Moore string search algorithm
 * Efficient algorithm for substring search.
 * https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string-search_algorithm
 */
public class BoyerMoore {

    private final int R;
    private int[] right;
    private String pattern;

    public BoyerMoore(String pat) {
        this.pattern = pat;
        this.R = 256; // extended ASCII
        this.right = new int[R];

        // Initialize all occurrences as -1
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }

        // Fill the actual value of last occurrence of a character
        for (int j = 0; j < pat.length(); j++) {
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String text) {
        if (pattern.isEmpty()) return 0;

        int m = pattern.length();
        int n = text.length();

        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                char txtChar = text.charAt(i + j);
                char patChar = pattern.charAt(j);
                if (patChar != txtChar) {
                    skip = Math.max(1, j - right[txtChar]);
                    break;
                }
            }
            if (skip == 0) return i; // found
        }
        return -1; // not found
    }

    public static int search(String text, String pattern) {
        return new BoyerMoore(pattern).search(text);
    }
}
