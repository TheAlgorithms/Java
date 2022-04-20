package com.thealgorithms.maths;

/**
 * Find the area of a triangle using only side lengths
 */

public class HeronsFormula {

	public static void main(String[] args)
	{
		assert Double.compare(Herons(3,4,5), 6.0) == 0;
		assert Double.compare(Herons(24,30,18), 216.0) == 0;;
		assert Double.compare(Herons(1,1,1), 0.4330127018922193) == 0;
		assert Double.compare(Herons(4,5,8), 8.181534085976786) == 0;
	}

	public static double Herons(int s1, int s2, int s3)
	{
		double a = s1;
		double b = s2;
		double c = s3;
		double s = (a + b + c)/2.0;
		double area = 0;
		area = Math.sqrt((s)*(s-a)*(s-b)*(s-c));
		return area;
	}
}
