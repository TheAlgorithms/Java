/*The algorithm reduces the multiplication of two n-digit numbers to 
three multiplications of n/2-digit numbers1. This is achieved by splitting 
each number into two halves and recursively applying the same process until 
the numbers are small enough to be multiplied directly3.
The key insight of the Karatsuba algorithm is that it performs fewer multiplications
in each recursive step. While the traditional multiplication 
algorithm performs four multiplications for two 2-digit numbers, the Karatsuba
algorithm only performs three
This makes the Karatsuba algorithm faster than the traditional multiplication algorithm,
especially for large numbers. The time complexity of the Karatsuba algorithm is O(n^1.585),
compared to O(n^2) for the traditional algorithm4.*/

//package com.thealgorithms.maths;
import java.math.BigInteger;

public class KaratSubaMultiplication {
	/**
	 * Finds the absolute maximum value among the given numbers.
	 *
	 * @param Multiple Two Number
	 * @return product
	 */
	static BigInteger multiple(BigInteger a, BigInteger b) {
		int n = a.toString().length();
		int m = b.toString().length();
		n = Math.max(n, m);
		m = n / 2;
		if (n == 1 || m == 1) {
			return a.multiply(b);
		}
		BigInteger x1, x0, y1, y0;
		x1 = a.divide(BigInteger.valueOf(10).pow(m / 2));
		y1 = b.divide(BigInteger.valueOf(10).pow(m / 2));
		x0 = a.mod(BigInteger.valueOf(10).pow(m / 2));
		y0 = b.mod(BigInteger.valueOf(10).pow(m / 2));
		BigInteger p = multiple(x1.add(x0), y1.add(y0));
		BigInteger x1y1 = multiple(x1, y1);
		BigInteger x0y0 = multiple(x0, y0);

		return x1y1.multiply(BigInteger.valueOf(10).pow(m))
				.add((p.subtract(x1y1).subtract(x0y0)).multiply(BigInteger.valueOf(10).pow(m / 2))).add(x0y0);
	}
}
