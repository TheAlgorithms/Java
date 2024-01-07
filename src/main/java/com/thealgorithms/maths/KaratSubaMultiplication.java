package com.thealgorithms.maths;
import java.math.BigInteger;
public class KaratSubaMultiplication {
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
		return x1y1.multiply(BigInteger.valueOf(10).pow(m)).add((p.subtract(x1y1).subtract(x0y0)).multiply(BigInteger.valueOf(10).pow(m / 2))).add(x0y0);
	}
}
