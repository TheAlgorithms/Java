package com.thealgorithms.datastructures.stacks;

import java.util.Stack;

public class LargestAreaInHistogram {
    static int getMaxArea(int arr[]) {
        int temp = 0;
        int n = arr.length;
        
        for (int i = 0; i < n; i++) {
            int current = arr[i];
            
            // Checking for previous area
            Stack<Integer> leftStack = new Stack<>();
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] >= arr[i]) {
                    current += arr[i];
                } else {
                    break;
                }
            }
            
            // Checking for next area
            Stack<Integer> rightStack = new Stack<>();
            for (int j = i + 1; j < n; j++) {
                if (arr[j] >= arr[i]) {
                    current += arr[i];
                } else {
                    break;
                }
            }
            
            temp = Math.max(temp, current);
        }
        return temp;
    }

    public static void main(String[] args) {
        int[] a = {6, 2, 5, 4, 5, 6};
        System.out.println(getMaxArea(a));
    }
}
