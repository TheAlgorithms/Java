package com.thealgorithms.dynamicprogramming;

public class TrappingRainWater {

    /**
     * Calculates total water trapped between the pillars.
     *
     * @param height array of non-negative integers representing pillar heights
     * @return total units of trapped water
     */
    public static int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int sum = 0;
        int topmax = 0;
        int temp2 = 0;

        // Find the index of the tallest pillar
        for (int i = 0; i < n; i++) {
            if (height[i] > temp2) {
                topmax = i;
                temp2 = height[i];
            }
        }

        int temp = height[0];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                sum += Math.min(height[i], height[topmax]) - height[i];
            } else if (i > 0 && i < topmax) {
                if (temp < height[i]) {
                    temp = height[i];
                    sum += Math.min(height[i], height[topmax]) - height[i];
                } else {
                    sum += Math.min(temp, height[topmax]) - height[i];
                }
            } else if (i == topmax) {
                // do nothing
            } else if (i > topmax) {
                int temp1 = height[i];
                int j = i;
                while (j < n - 1) {
                    if (temp1 < height[j + 1]) {
                        temp1 = height[j + 1];
                    }
                    j++;
                }
                sum += Math.min(height[topmax], temp1) - height[i];
            }
        }

        return sum;
    }
}
