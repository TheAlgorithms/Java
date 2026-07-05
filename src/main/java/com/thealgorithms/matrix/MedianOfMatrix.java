package com.thealgorithms.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Median of Matrix (<a href="https://medium.com/@vaibhav.yadav8101/median-in-a-row-wise-sorted-matrix-901737f3e116">...</a>)
 * Author: Bama Charan Chhandogi (<a href="https://github.com/BamaCharanChhandogi">...</a>)
 */

public final class MedianOfMatrix {
    private MedianOfMatrix() {
    }

    public static int median(Iterable<List<Integer>> matrix) {
        List<Integer> flattened = new ArrayList<>();

        for (List<Integer> row : matrix) {
            if (row != null) {
                flattened.addAll(row);
            }
        }

        if (flattened.isEmpty()) {
            throw new IllegalArgumentException("Matrix must contain at least one element.");
        }

        Collections.sort(flattened);
        return flattened.get((flattened.size() - 1) / 2);
    }
}
