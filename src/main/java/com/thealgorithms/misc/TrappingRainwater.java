package com.thealgorithms.misc;
import java.util.*;

/**
 *
 *
 * <h1>Trapping Rain Water</h1>
 *
 * Time Complexity - O(n)
 *  Given an array of N non-negative integers arr[] representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after         raining.
 * <p>
 * <b>Note:</b> Giving proper comments in your program makes it more user
 * friendly and it is assumed as a high quality code.
 */
public class TrappingRainwater {

    public static long trappedRainwater(int height[]){
        int n = height.length;
        int waterLevel =0;
        int trappedWater =0;
        // Calculate Left Max Boundary array
        int leftMax[] = new int[n];
        leftMax[0]=height[0];
        for(int i=1;i<n;i++) {
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }
        // Calculate Right Max Boundary array

        int rightMax[] = new int[n];
        rightMax[n-1] = height[n-1];
        for(int i=n-2;i>=0;i--){
            rightMax[i] = Math.max(height[i], rightMax[i+1]);
        }
        //loops
        for(int i=0;i<n;i++) {
            // WaterLevel = min(leftMax , rightMax)
            waterLevel = Math.min(leftMax[i], rightMax[i]);
            // TrappedWater = waterLevel - height[i] * width;
            trappedWater += waterLevel - height[i]; //width =1
        }
        return trappedWater;
    }
  public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // Length of the array
        int n = sc.nextInt();
        // Array Input
        int height[] = new int[n];
        for(int i=0;i<n;i++){
            height[i]= sc.nextInt();
        }
        // Calling Function
        System.out.println("Trapped Rainwater = "+ trappedRainwater(height));
    }
}
