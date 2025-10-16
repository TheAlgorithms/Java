package com.thealgorithms.compression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of LZW (Lempel–Ziv–Welch) compression algorithm.
 * Reference: https://en.wikipedia.org/wiki/Lempel–Ziv–Welch
 */

public final class LZW {

    private LZW() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static List<Integer> compress(String input) {
        int dictSize = 256;
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }

        String w = "";
        List<Integer> result = new ArrayList<>();

        for (char c : input.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc)) {
                w = wc;
            } else {
                result.add(dictionary.get(w));
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        if (!w.isEmpty()) {
            result.add(dictionary.get(w));
        }

        return result;
    }
}
