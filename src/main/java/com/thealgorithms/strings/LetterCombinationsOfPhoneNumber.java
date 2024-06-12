package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class LetterCombinationsOfPhoneNumber {

    // Mapping of numbers to corresponding letters on a phone keypad
    private static final Map<Integer, String> numberToCharMap = Map.of( //
        0, "", //
        1, "", //
        2, "abc", //
        3, "def", //
        4, "ghi", //
        5, "jkl", //
        6, "mno", //
        7, "pqrs", //
        8, "tuv", //
        9, "wxyz");

    private LetterCombinationsOfPhoneNumber() {
        // Prevent instantiation
    }

    /**
     * Generates a list of all possible letter combinations that the provided
     * array of numbers could represent on a phone keypad.
     *
     * @param numbers an array of integers representing the phone numbers
     * @return a list of possible letter combinations
     */
    public static List<String> getCombinations(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return List.of("");
        }
        return generateCombinations(numbers, 0, new StringBuilder());
    }

    /**
     * Recursive method to generate combinations of letters from the phone keypad.
     *
     * @param numbers the input array of phone numbers
     * @param index   the current index in the numbers array being processed
     * @param current a StringBuilder holding the current combination of letters
     * @return a list of letter combinations formed from the given numbers
     */
    private static List<String> generateCombinations(int[] numbers, int index, StringBuilder current) {
        // Base case: if we've processed all numbers, return the current combination
        if (index == numbers.length) {
            return new ArrayList<>(Collections.singletonList(current.toString()));
        }

        List<String> combinations = new ArrayList<>();
        String letters = numberToCharMap.get(numbers[index]); // Get corresponding letters for the current number

        // Iterate over each letter and recurse to generate further combinations
        for (char letter : letters.toCharArray()) {
            current.append(letter); // Append the current letter
            combinations.addAll(generateCombinations(numbers, index + 1, current)); // Recursive call
            current.deleteCharAt(current.length() - 1); // Backtrack by removing the last appended letter
        }

        return combinations;
    }

    /**
     * Main method for testing the letter combination generation.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int[] numbers = {2, 3, 4};
        List<String> combinations = getCombinations(numbers);
        combinations.forEach(System.out::println); // Print each combination
    }
}
