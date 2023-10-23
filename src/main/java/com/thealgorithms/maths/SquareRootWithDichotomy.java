package com.thealgorithms.maths;

/**
 * Calculate the square root of x using the bisection method
 *
 * @author yuluo
 */

public class SquareRootWithDichotomy {

	public static void main(String[] args) {
		assert squareRootWithDichotomy(4) == Math.sqrt(4);
		System.out.println(squareRootWithDichotomy(4) + " " + (int) Math.sqrt(4));
		assert squareRootWithDichotomy(8) == Math.sqrt(8);
		System.out.println(squareRootWithDichotomy(8) + " " + (int) Math.sqrt(8));
	}

	public static int squareRootWithDichotomy(int x) {

		if (x == 0) {
			return 0;
		}

		int left = 0, right = x, res = -1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if ((long) mid * mid <= x) {
				res = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return res;
	}
}
