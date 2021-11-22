package com.thealgorithms.maths;

/*
 * Java program for Binomial Cofficients
 * Binomial Cofficients: A binomial cofficient C(n,k) gives number ways
 * in which k objects can be chosen from n objects.
 * Wikipedia: https://en.wikipedia.org/wiki/Binomial_coefficient
 * 
 * Author: Akshay Dubey (https://github.com/itsAkshayDubey)
 * 
 * */

public class BinomialCoefficient {
	
	/**
     * This method returns the number of ways in which k objects can be chosen from n objects
     *
     * @param total_objects Total number of objects
     * @param no_of_objects Number of objects to be chosen from total_objects
     * @return number of ways in which no_of_objects objects can be chosen from total_objects objects
     */
	
	static int binomialCoefficient(int total_objects, int no_of_objects) {
		
		//Base Case
		if(no_of_objects > total_objects) {
			return 0;
		}
		
		//Base Case
		if(no_of_objects == 0 || no_of_objects == total_objects) {
			return 1;
		}
		
		//Recursive Call
		return binomialCoefficient(total_objects - 1, no_of_objects - 1)
				+ binomialCoefficient(total_objects - 1, no_of_objects);
	}
	
	public static void main(String[] args) {
		System.out.println(binomialCoefficient(20,2));
		
		//Output: 190
	}

}
