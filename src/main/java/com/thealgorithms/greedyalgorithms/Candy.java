package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.List;

/*
Problem Statement:
There are n children standing in a line.
Each child is assigned a rating value given in the integer array ratings.

You are giving candies to these children subjected to the following requirements:
(i) Each child must have at least one candy.
(ii) Children with a higher rating get more candies than their neighbors.

Return the min no. of candies you need to have to distribute the candies to the children.
*/

// Time Complexity: O(n)
// Space Complexity: O(n)
public class Candy {
    public static int candy(List<Integer> ratings) {
        int n = ratings.size();

        int ans = 0;
        List<Integer> left = new ArrayList<>(n);
        List<Integer> right = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            left.add(1);
            right.add(1);
        }

        // Traverse from left to right
        for (int i = 1; i < n; i++) {
            if (ratings.get(i) > ratings.get(i - 1))
                left.set(i, left.get(i - 1) + 1);
        }

        // Traverse from right to left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings.get(i) > ratings.get(i + 1))
                right.set(i, right.get(i + 1) + 1);
        }

        // Take the max of left and right for each child
        for (int i = 0; i < n; i++)
            ans += Math.max(left.get(i), right.get(i));

        return ans;
    }

}
