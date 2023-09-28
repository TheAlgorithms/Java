package com.thealgorithms.misc;
import java.util.*;


/**
 * Solution for The Four Sum Problem.
 * @author mouss3abbb
 */
public class FourSumProblem {

    /*
    In the hashing solution, we maintain a hashmap of the sum of each pair of elements in the array.
    We then iterate over each pair of elements in the array and check if the required sum to be equal to target is found in the map previously.
    The map now acts as a frequency table. However, we also need to check that the indices of all four elements are distinct, that's why we maintain a pair of indices in the map itself and compare it with the pair of elements we are at right now.
    */
    public static List<List<Integer>> Hashing(ArrayList<Integer> arr, int target) {
        Set<List<Integer>> set = new HashSet<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                List<Integer> idx = new ArrayList<>();
                idx.add(i);
                idx.add(j);
                map.put(arr.get(i) + arr.get(j), idx);
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                int t = target - (arr.get(i) + arr.get(j));
                if (map.containsKey(t) && map.get(t).get(0) != j && map.get(t).get(1) != j && map.get(t).get(0) != i && map.get(t).get(1) != i) {
                    List<Integer> sum = new ArrayList<>();
                    sum.add(arr.get(i));
                    sum.add(arr.get(j));
                    sum.add(arr.get(map.get(t).get(0)));
                    sum.add(arr.get(map.get(t).get(1)));
                    Collections.sort(sum);
                    set.add(sum);
                }
            }
        }
        return new ArrayList<>(set);
    }
}
