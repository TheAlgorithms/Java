package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
/*
This class finds the majority element(s) in an array/List of integers.
A majority element is an element that appears more than or equal to n/2 times, where n is the length
of the array.
*/
public final class MajorityElement {
    private MajorityElement(){}

   /**
     * This method uses Moore's voting algorithm to find the majority element in the list.
     * If a majority element is present, it returns the element; otherwise, it returns Optional.empty().
     * @param <T> The type of elements in the list
     * @param elements List of any type
     * @return Optional of majority element or empty if none found
     */
    public static <T> Optional<T> majorityElement(List<T> elements) {
        if (elements == null || elements.isEmpty()) {
            return Optional.empty();  // Handle empty list case
        }

        T currentElement = elements.get(0);
        long frequency = 1;

        // Moore's Voting Algorithm to find potential majority element
        for (int i = 1; i < elements.size(); i++) {
            if (frequency == 0) {
                currentElement = elements.get(i);
                frequency = 1;
            } else if (!currentElement.equals(elements.get(i))) {
                frequency--;
            } else {
                frequency++;
            }
        }

        // Second pass to confirm if it occurs more than n/2 times
        long count = 0;
        for (T element : elements) {
            if (element.equals(currentElement)) {
                count++;
            }
        }

        if (count > elements.size() / 2) {
            return Optional.of(currentElement);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Method to handle array input and convert to list.
     * @param <T> The type of elements in the array
     * @param elements Array of any type
     * @return Optional of majority element or empty if none found
     */
    public static <T> Optional<T> majorityElement(T[] elements) {
        if (elements == null || elements.length == 0) {
            return Optional.empty();  // Handle empty array case
        }
        return majorityElement(Arrays.asList(elements));
    }
    
    /*
   This method returns the majority element(s) in the given array of integers.
   @param nums: an array of integers
   @return a list of majority elements
   */
    public static List<Integer> majority(int[] nums) {
        HashMap<Integer, Integer> numToCount = new HashMap<>();
        for (final var num : nums) {
            numToCount.merge(num, 1, Integer::sum);
        }
        List<Integer> majorityElements = new ArrayList<>();
        for (final var entry : numToCount.entrySet()) {
            if (entry.getValue() >= nums.length / 2) {
                majorityElements.add(entry.getKey());
            }
        }
        return majorityElements;
    }
}
