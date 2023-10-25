package com.thealgorithms.bitmanipulation;

// Given a decimal value, this code can find what will be the equivalent number of digits for that number when converted to that particular base
// example , when 13 in decimal is converted with base 2, it gives output 4, as the binary equivalent is 1101, which has 4 places

public class NumberOfDigitsForBase {

	public static int numberOfDigitsForBase (int number, int base) {
		double value = Math.log(number) / Math.log(base);
		return (int) Math.floor(value) + 1;
	}
}
