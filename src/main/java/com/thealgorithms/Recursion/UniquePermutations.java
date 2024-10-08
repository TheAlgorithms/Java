package com.thealgorithms.Recursion;

public class UniquePermutations {
    throw new UnsupportedOperationException("Utility class");


    public static List<String> getUniquePermutations(String str) {
        List<String> result = new ArrayList<>();
        boolean[] used = new boolean[str.length()];
        char[] chars = str.toCharArray();
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

