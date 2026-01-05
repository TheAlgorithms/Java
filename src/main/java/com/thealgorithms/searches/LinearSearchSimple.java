package com.thealgorithms.searches;
public class LinearSearchSimple {
    public static int LinearSearchSimple(int[] arr, int target) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 5, 1};
        int target = 5;
        int result = LinearSearchSimple(arr, target);
        if(result != -1) {      
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found in the array.");
        }
    }
}