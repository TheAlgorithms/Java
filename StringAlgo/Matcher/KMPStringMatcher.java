import java.util.ArrayList;
import java.util.List;

public class KMPStringMatcher {

    /**
     * @param texts array of strings
     * @return true if at least one string is null or empty, else false
     */
    private static boolean isEmptyString(String... texts) {
        for (String str : texts) {
            if (str == null || str.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param text    string text
     * @param pattern string pattern
     * @return list of integers containing start indexes of pattern
     * Preprocessing time: Theta(m)
     * Matching time: Theta(n)
     * If there is a mismatch, using prefix function we can avoid next characters which will match anyway.
     * We keep updating number of characters matched until we hit miss match
     * If number of characters matched is equal to the length of pattern, we found new pattern index;
     */
    public static List<Integer> match(String text, String pattern) {
        List<Integer> list = new ArrayList<>();
        if (isEmptyString(text, pattern)) {
            return list;
        }
        int n = text.length();
        int m = pattern.length();
        int[] prefixFunction = computePrefixFunction(pattern);
        int q = 0; //number of characters matched so far
        for (int i = 0; i <= (n - 1); i++) {
            while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
                q = prefixFunction[q - 1];
            }
            if (pattern.charAt(q) == text.charAt(i)) {
                q++;
            }
            if (q == m) {
                list.add(i - m + 1);
                q = prefixFunction[q - 1];
            }
        }
        return list;
    }

    /**
     * @param text    input text
     * @param pattern input pattern to be found in text
     * case sensitive
     * preprocessing time: 0
     * Matching time: O((n-m+1)*m)
     * Prints all pattern occurrences to stdout
     */
    public static void matchPrint(String text, String pattern) {
        for (int index : match(text, pattern)) {
            System.out.print("Pattern occurs with shift\t" + index + "\n");
        }
    }

    /**
     * @param pattern String pattern
     * @return array of integers
     * The prefix function for a pattern encapsulates knowledge about how the pattern matches against shifts of itself
     * This information helps to avoid testing useless shifts in the naive string matcher.
     * Prefix function indicates the longest proper prefix which is also suffix
     */
    private static int[] computePrefixFunction(String pattern) {
        int m = pattern.length();
        int[] result = new int[m];
        result[0] = 0;
        int k = 0;
        for (int q = 1; q <= (m - 1); q++) {
            while (k > 0 && (pattern.charAt(k + 1) != pattern.charAt(q))) {
                k = result[k];
            }
            if (pattern.charAt(k + 1) == pattern.charAt(q)) {
                k++;
            }
            result[q] = k;
        }
        return result;
    }

    /**
     * Private constructor to avoid creating object of this class, since it is utility class
     */
    private KMPStringMatcher() {
        throw new IllegalStateException("Utility class");
    }

    public static void main(String[] args){
        String temp = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).";
        String word = "is";
        KMPStringMatcher.matchPrint(temp, word);
    }
}
