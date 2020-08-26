package strings;

import java.util.Arrays;

/**
 * Two strings are anagrams if they are made of the same letters
 * arranged differently (ignoring the case).
 */
public class CheckAnagrams {
    public static void main(String[] args) {
        assert isAnagrams("Silent", "Listen");
        assert isAnagrams("This is a string", "Is this a string");
        assert !isAnagrams("There", "Their");
    }

    /**
     * Check if two strings are anagrams or not
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return {@code true} if two string are anagrams, otherwise {@code false}
     */
    public static boolean isAnagrams(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        char[] values1 = s1.toCharArray();
        char[] values2 = s2.toCharArray();
        Arrays.sort(values1);
        Arrays.sort(values2);
        return new String(values1).equals(new String(values2));
    }
}
