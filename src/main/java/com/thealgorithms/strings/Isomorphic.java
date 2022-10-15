package com.thealgorithms.strings;

import java.util.*;

public class Isomorphic {

    public static boolean checkStrings(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // To mark the characters of string using MAP
        // character of first string as KEY and another as VALUE
        // now check occurence by keeping the track with SET data structure
        Map<Character, Character> characterMap = new HashMap<Character, Character>();
        Set<Character> trackUinqueCharacter = new HashSet<Character>();

        for (int i = 0; i < s.length(); i++) {
            if (characterMap.containsKey(s.charAt(i))) {
                if (t.charAt(i) != characterMap.get(s.charAt(i))) {
                    return false;
                }
            } else {
                if (trackUinqueCharacter.contains(t.charAt(i))) {
                    return false;
                }

                characterMap.put(s.charAt(i), t.charAt(i));
            }
            trackUinqueCharacter.add(t.charAt(i));
        }
        return true;
    }
}
