/**
 * AnagramChecker.java
 * Checks whether two input strings are anagrams using a HashMap.
 * Time Complexity: O(n), Space Complexity: O(n)
 */

import java.util.HashMap;

public class AnagramChecker {

    public static boolean areAnagrams(String str1, String str2) {
        // Remove spaces and convert to lowercase
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // Quick length check
        if (str1.length() != str2.length()) {
            return false;
        }

        // Count characters in first string
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Subtract character counts using second string
        for (char c : str2.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }

        return map.isEmpty();
    }

    public static void main(String[] args) {
        String s1 = "Listen";
        String s2 = "Silent";

        if (areAnagrams(s1, s2)) {
            System.out.println("\"" + s1 + "\" and \"" + s2 + "\" are anagrams.");
        } else {
            System.out.println("\"" + s1 + "\" and \"" + s2 + "\" are NOT anagrams.");
        }
    }
}