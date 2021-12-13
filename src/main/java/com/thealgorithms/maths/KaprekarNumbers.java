package com.thealgorithms.maths;

public class KaprekarNumbers {
	
	public static void main(String[] args) {

		/* This program demonstrates if a given number is Kaprekar Number or not.
		Kaprekar Number: A Kaprekar number is an n-digit number which its square can be split into two parts where the right part has n
		digits and sum of these parts is equal to the original number. */

		// Kaprekar Numbers
		System.out.println(isKaprekarNumber(1));
		System.out.println(isKaprekarNumber(9));
		System.out.println(isKaprekarNumber(45));
		System.out.println(isKaprekarNumber(55));
		System.out.println(isKaprekarNumber(99));
		System.out.println(isKaprekarNumber(297));
		System.out.println(isKaprekarNumber(703));
		System.out.println(isKaprekarNumber(999));
		System.out.println(isKaprekarNumber(2223));
		System.out.println(isKaprekarNumber(2728));
		System.out.println(isKaprekarNumber(4879));
		System.out.println(isKaprekarNumber(5292));
		System.out.println(isKaprekarNumber(857143));

		// None Kaprekar Numbers
		System.out.println(isKaprekarNumber(3));
		System.out.println(isKaprekarNumber(11));
		System.out.println(isKaprekarNumber(13));
		System.out.println(isKaprekarNumber(26));
		System.out.println(isKaprekarNumber(56));
		System.out.println(isKaprekarNumber(98));

	}

	// Checks whether a given number is Kaprekar Number or not

	public static boolean isKaprekarNumber(long number) {			
		long numberSquared = number * number;
		if(Long.toString(number).length() == Long.toString(numberSquared).length()){
			return (number == numberSquared);
		}
		else{
			long leftDigits1 = 0, leftDigits2 = 0;
			if(Long.toString(numberSquared).contains("0")){
				leftDigits1 = Long.parseLong(Long.toString(numberSquared).substring(0, Long.toString(numberSquared).indexOf("0")));
			}
			leftDigits2 = Long.parseLong(Long.toString(numberSquared).substring(0, (Long.toString(numberSquared).length() - Long.toString(number).length())));
			long rightDigits = Long.parseLong(Long.toString(numberSquared).substring(Long.toString(numberSquared).length() - Long.toString(number).length()));
			return (number == (leftDigits1 + rightDigits)) || (number == (leftDigits2 + rightDigits));
		}		
	}

}
