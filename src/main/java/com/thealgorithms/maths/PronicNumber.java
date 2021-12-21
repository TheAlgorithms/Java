package com.thealgorithms.maths;

/*
 * Java program for Pronic Number
 * Pronic Number: A number n is a pronic number if
 * it is equal to product of two consecutive numbers m and m+1.
 * Wikipedia: https://en.wikipedia.org/wiki/Pronic_number
 * 
 * Author: Akshay Dubey (https://github.com/itsAkshayDubey)
 * 
 * */

public class PronicNumber {

	/**
     * This method checks if the given number is pronic number or non-pronic number
     *
     * @param input_number Integer value which is to be checked if is a pronic number or not 
     * @return true if input number is a pronic number, false otherwise
     */
	static boolean isPronic(int input_number) {
		
		//Iterating from 0 to input_number
		for(int i = 0; i <= input_number; i++) {
			
			//Checking if product of i and (i+1) is equals input_number
			if(i * (i+1) == input_number && i != input_number) {
				
				//return true if product of i and (i+1) is equals input_number
				return true;
			}
			
		}
		
		//return false if product of i and (i+1) for all values from 0 to input_number is not equals input_number
		return false;
	}
}
