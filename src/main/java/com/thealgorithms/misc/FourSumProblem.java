package com.thealgorithms.misc;

import java.util.*;

/**
 * Solution for The Four Sum Problem.
 *
 * @author mouss3abbb
 */
public final class FourSumProblem {

    /*
    In the hashing solution, we maintain a hashmap of the sum of each pair of elements in the array.
    We then iterate over each pair of elements in the array and check if the required sum to be equal to target is found in the map previously.
    The map now acts as a frequency table. However, we also need to check that the indices of all four elements are distinct, that's why we maintain a pair of indices in the map itself and compare it with the pair of elements we are at right now.
    */
    public static List<List<Integer>> hashing(final ArrayList<Integer> arr, final int target) {
        Set<List<Integer>> set = new HashSet<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // Find the sum of each pair of elements and store it in the map
        findAllPairSum(arr, map);
        // We iterate over each pair of elements and check if their sum added to any value in the map will equal the target
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                checkRemainingSumAvailability(arr, target, i, j, map, set);
            }
        }
        return new ArrayList<>(set);
    }

    private static void checkRemainingSumAvailability(ArrayList<Integer> arr, int target, int i, int j, HashMap<Integer, List<Integer>> map, Set<List<Integer>> set) {
        int remainingSum = target - (arr.get(i) + arr.get(j));
        // We need to check is we have taken any of these indices before to avoid taking the same index twice.
        if (isThisIndexTaken(i, j, map, remainingSum)) {
            List<Integer> sum = new ArrayList<>();
            sum.add(arr.get(i));
            sum.add(arr.get(j));
            sum.add(arr.get(map.get(remainingSum).get(0)));
            sum.add(arr.get(map.get(remainingSum).get(1)));
            Collections.sort(sum);
            set.add(sum);
        }
    }

    private static boolean isThisIndexTaken(int i, int j, HashMap<Integer, List<Integer>> map, int remainingSum) {
        return map.containsKey(remainingSum) && map.get(remainingSum).get(0) != j && map.get(remainingSum).get(1) != j && map.get(remainingSum).get(0) != i && map.get(remainingSum).get(1) != i;
    }

    private static void findAllPairSum(ArrayList<Integer> arr, HashMap<Integer, List<Integer>> map) {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                List<Integer> idx = new ArrayList<>();
                idx.add(i);
                idx.add(j);
                map.put(arr.get(i) + arr.get(j), idx);
            }
        }
    }
}
