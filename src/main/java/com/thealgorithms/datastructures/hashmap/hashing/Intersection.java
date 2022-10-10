package com.thealgorithms.datastructures.hashmap.hashing;

/*
 * this is algo which implies common mathematical set theory concept
 * called intersection in which result is common values of both the sets
 * here metaphor of sets is HashMap
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Intersection {

    public static List<Integer> intersection(int[] arr1, int[] arr2) {
        if (
            arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0
        ) {
            return Collections.emptyList();
        }
        Map<Integer, Integer> cnt = new HashMap<>(16);
        for (int v : arr1) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int v : arr2) {
            if (cnt.containsKey(v) && cnt.get(v) > 0) {
                res.add(v);
                cnt.put(v, cnt.get(v) - 1);
            }
        }
        return res;
    }

    private Intersection() {}
}
