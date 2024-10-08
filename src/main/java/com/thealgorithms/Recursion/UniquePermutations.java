package com.thealgorithms.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniquePermutations {

    public static List<String> getUniquePermutations(String str) {
        // Handle null or empty input
        if (str == null) return new ArrayList<>();
        if (str.length() == 0) {
            List<String> result = new ArrayList<>();
            result.add("");
            return result;
        }
        
        // Sort characters to handle duplicates
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        
        List<String> result = new ArrayList<>();
        boolean[] used = new boolean[chars.length];
        StringBuilder currentPermutation = new StringBuilder();
        generatePermutations(chars, used, currentPermutation, result);
        return result;
    }

    private static void generatePermutations(char[] chars, boolean[] used, StringBuilder currentPermutation, List<String> result) {
        if (currentPermutation.length() == chars.length) {
            result.add(currentPermutation.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            // Skip used characters or duplicates
            if (used[i] || (i > 0 && chars[i] == chars[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            currentPermutation.append(chars[i]);
            generatePermutations(chars, used, currentPermutation, result);
            used[i] = false;
            currentPermutation.deleteCharAt(currentPermutation.length() - 1);
        }
    }
}


//This is a more efficient but complex algorithm
//If you want to refer to a simpler one and then come to this,
//click on the URL=>"https://www.geeksforgeeks.org/java-program-to-print-distinct-permutations-of-a-string/""
