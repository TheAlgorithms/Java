package StringMatch;

import java.util.ArrayList;
import java.util.List;

public class NaiveStringMatcher implements Matcher{
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
     *                case sensitive
     *                preprocessing time: 0
     *                Matching time: O((n-m+1)*m)
     */
    public static void matchPrint(String text, String pattern) {
        for (int index : match(text, pattern)) {
            System.out.println("Pattern occurs with shift\t" + index);
        }
    }


    //there must not be object of this class
    private NaiveStringMatcher() {
        throw new IllegalStateException("Utility class");
    }

}
