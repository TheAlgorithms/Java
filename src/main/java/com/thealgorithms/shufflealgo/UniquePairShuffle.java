package com.thealgorithms.shufflealgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public
final class UniquePairShuffle {

    private
    UniquePairShuffle() {
        // Prevent instantiation
    }

    /**
     * Pairs each element in the array with another element randomly, ensuring no
     * pair repeats. If the array length is odd, pairing cannot be completed, so
     * an empty list is returned.
     *
     * @param array the input array to pair elements from
     * @return a list of unique pairs where each pair is represented as an integer
     * array of length 2
     */
    public
    static List<int[]> pairShuffle(int[] array) {
        List<int[]> pairs = new ArrayList<>();

        // Handle edge case: If the array length is odd, pairing is not possible
        if (array.length < 2 || array.length % 2 != 0) {
            return pairs;
        }

        List<Integer> shuffledList = new ArrayList<>();
        for (int num : array) {
            shuffledList.add(num);
        }

        // Shuffle elements to create random pairs
        Collections.shuffle(shuffledList);

        // Form pairs from the shuffled elements
        for (int i = 0; i < shuffledList.size(); i += 2) {
            pairs.add(new int[]{shuffledList.get(i), shuffledList.get(i + 1)});
        }

        return pairs;
    }

    public
    static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        List<int[]> pairs = pairShuffle(array);

        System.out.println("Generated Unique Pairs:");
        for (int[] pair : pairs) {
            System.out.println(pair[0] + " - " + pair[1]);
        }
    }
}
