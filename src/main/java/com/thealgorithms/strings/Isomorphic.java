package com.thealgorithms.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Isomorphic {
    private Isomorphic() {
    }

    public static boolean checkStrings(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // To mark the characters of string using MAP
        // character of first string as KEY and another as VALUE
        // now check occurence by keeping the track with SET data structure
        Map<Character, Character> characterMap = new HashMap<>();
        Set<Character> trackUniqueCharacter = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (characterMap.containsKey(s.charAt(i))) {
                if (t.charAt(i) != characterMap.get(s.charAt(i))) {
                    return false;
                }
            } else {
                if (trackUniqueCharacter.contains(t.charAt(i))) {
                    return false;
                }

                characterMap.put(s.charAt(i), t.charAt(i));
            }
            trackUniqueCharacter.add(t.charAt(i));
        }
        return true;
    }
}
