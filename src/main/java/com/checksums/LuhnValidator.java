package com.checksums;

/**
 * Luhn algorithm is a simple checksum formula used to validate a variety of identifications numbers,
 * credit card numbers etc. </br>
 * @see <a href="https://en.wikipedia.org/wiki/Luhn_algorithm">Wikipedia Luhn Algorithm page</a>
 * @author Julien Gracia
 *
 */
public class LuhnValidator {

	/**
	 * Check if the candidate is valid to Luhn Algorithm
	 * @param candidate The candidate, a string of numbers
	 * @return a boolean
	 */
	public boolean isValid(String candidate) {
		
		String candidateClean = candidate.trim().replace(" ", "");
		
		if(candidateClean.length() <= 1) {
			return false;
		}
		
		int nSum = 0;
		boolean isSecond = false;
		for (int i = candidateClean.length() - 1; i >= 0; i--) {

			int d = candidateClean.charAt(i) - '0';

			if (isSecond)
				d = d * 2;

			// We add two digits to handle cases that make two digits
			// after doubling
			nSum += d / 10;
			nSum += d % 10;

			isSecond = !isSecond;
		}
		return (nSum % 10 == 0);
	}

}
