package com.thealgorithms.maths;

public class DistanceFormula {
	public static double distance(double x1, double y1, double x2, double y2)
	{
		double dX = Math.pow(x2-x1, 2);
		double dY = Math.pow(y2-x1, 2);
		double d = Math.sqrt(dX+dY);
		return d;
	}
}
