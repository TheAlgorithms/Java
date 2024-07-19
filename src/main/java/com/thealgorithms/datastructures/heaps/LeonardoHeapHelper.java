package com.thealgorithms.datastructures.heaps;

import java.util.ArrayList;
import java.util.List;

// TODO: CHeck file name. Check if the file needs to be here or in bitwose operations.
public final class LeonardoHeapHelper {

    private LeonardoHeapHelper() {
    }

    public static ArrayList<Integer> findConsecutiveTreeIndices(int num) {
        int prevOneIndex = -1;
        int currentLevel = 0;

        ArrayList<Integer> answer = new ArrayList<Integer>();
        answer.add(-1);
        answer.add(-1);

        for (int i = 0; num > 0; i++) {
            currentLevel = num & 1;
            if (currentLevel == 1) {
                if (prevOneIndex != -1) {
                    answer.set(0, prevOneIndex);
                    answer.set(1, i);
                }
                prevOneIndex = i;
            } else {
                prevOneIndex = -1;
            }
            num >>>= 1;
        }
        return answer;
    }

    public static Integer[] findAllTreeIndices(int num) {
        List<Integer> setBitIndexes = new ArrayList<>();
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            if ((num & (1 << i)) != 0) {
                setBitIndexes.add(i);
            }
        }
        return setBitIndexes.toArray(new Integer[0]);
    }
}
