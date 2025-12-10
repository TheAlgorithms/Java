package com.thealgorithms.strings;

/**
 * Class for generating all subsequences of a given string.
 */
public final class ReturnSubsequence {
    private ReturnSubsequence() {
    }

    /**
     * Generates all subsequences of the given string.
     *
     * @param input The input string.
     * @return An array of subsequences.
     */
    public static String[] getSubsequences(String input) {
        if (input.isEmpty()) {
            return new String[] {""}; // Return array with an empty string if input is empty
        }

        // Recursively find subsequences of the substring (excluding the first character)
        String[] smallerSubsequences = getSubsequences(input.substring(1));

        // Create an array to hold the final subsequences, double the size of smallerSubsequences
        String[] result = new String[2 * smallerSubsequences.length];

        // Copy the smaller subsequences directly to the result array
        System.arraycopy(smallerSubsequences, 0, result, 0, smallerSubsequences.length);

        // Prepend the first character of the input string to each of the smaller subsequences
        for (int i = 0; i < smallerSubsequences.length; i++) {
            result[i + smallerSubsequences.length] = input.charAt(0) + smallerSubsequences[i];
        }

        return result;
    }
}
