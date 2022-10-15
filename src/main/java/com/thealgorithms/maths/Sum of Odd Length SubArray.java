package com.thealgorithms.maths;

class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        
        int sum =0;
        for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				for (int k = i; k <= j; k++) {

					if ( (i + j) % 2 == 0) {
						sum += arr[k];
					}
				}
			}
		}
		return sum;
    }
}
