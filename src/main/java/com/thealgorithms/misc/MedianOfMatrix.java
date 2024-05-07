package com.thealgorithms.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Median of Matrix (https://medium.com/@vaibhav.yadav8101/median-in-a-row-wise-sorted-matrix-901737f3e116)
 * Author: Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public final class MedianOfMatrix {
    private MedianOfMatrix() {
    }

    public static int median(List<List<Integer>> matrix) {
        // Flatten the matrix into a 1D list
        List<Integer> linear = new ArrayList<>();
        for (List<Integer> row : matrix) {
            linear.addAll(row);
        }

        // Sort the 1D list
        Collections.sort(linear);

        // Calculate the middle index
        int mid = (0 + linear.size() - 1) / 2;

        // Return the median
        return linear.get(mid);
    }
}
