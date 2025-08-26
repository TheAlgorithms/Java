package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Naive Pattern Searching algorithm.
 * Reference: https://en.wikipedia.org/wiki/String-searching_algorithm#Na%C3%AFve_string_search
 */
public class NaivePatternSearch {

    /**
     * Finds all occurrences of a pattern in a given text using
     * the naive substring search algorithm.
     *
     * @param text    The text in which to search.
     * @param pattern The pattern to search for.
     * @return List of starting indices where the pattern is found.
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            String sub = text.substring(i, i + m);
            if (sub.equals(pattern)) {
                result.add(i);
            }
        }
        return result;
    }
}
