package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * 
 * Find the area of a triangle using only side lengths
 *
 */
public class HeronsFormula {
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
	@Test
	public static void main(String[] args)
	{
		assertAll("triangles",
		        () -> assertEquals(Herons(3,4,5), 6.0),
		        () -> assertEquals(Herons(24,30,18), 216.0),
		        () -> assertEquals(Herons(1,1,1), 0.4330127018922193),
		        () -> assertEquals(Herons(4,5,8), 8.181534085976786));
	}
}
