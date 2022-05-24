package com.thealgorithms.maths;

public class StandardScore {
	public static double zScore(double mean, double num, double stdDev)
	{
		double z = (num - mean)/stdDev;
		return z;
	}

}
