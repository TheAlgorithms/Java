import java.util.ArrayList;
import java.util.List;

public class NaiveStringMatcher {

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
     * @param text    input text
     * @param pattern input pattern to be found in text
     * @return list of integers containing start indexes of pattern
     * case sensitive
     * preprocessing time: 0
     * Matching time: O((n-m+1)*m)
     * Finds all valid shifts using a loop that checks the condition Pattern[1...m] = Text[i+1...i+m] for each of the n-m+1 possible values of i.
     */
    public static List<Integer> match(String text, String pattern) {
        List<Integer> list = new ArrayList<>();
        if (isEmptyString(text, pattern)) {
            return list;
        }
        int n = text.length();
        int m = pattern.length();
        for (int i = 0; i <= (n - m); i++) {
            boolean patternOccured = true;
            int j = 0;
            while (j < m) {
                if (pattern.charAt(j) != text.charAt(j + i)) {
                    patternOccured = false;
                    break;
                }
                j++;
            }
            if (patternOccured) {
                list.add(i);
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
            System.out.println("Pattern occurs with shift\t" + index);
        }
    }

    /**
     * Private constructor to avoid creating object of this class, since it is utility class
     */
    private NaiveStringMatcher() {
        throw new IllegalStateException("Utility class");
    }

    public static void main(String[] args){
        String temp = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).";
        String word = "is";
        NaiveStringMatcher.matchPrint(temp, word);
    }
}
