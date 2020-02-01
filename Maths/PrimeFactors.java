package Maths;

import java.util.Random; 
import java.util.Set;
import java.util.HashSet;
import java.math.BigInteger;

public class PrimeFactors {
	public static void main(String[] args) {
		BigInteger number = new BigInteger(25, new Random());
		System.out.println("this is prime factors of your number : " + primeFactors(number));
	}

	/**
     * Calculate prime factors of the number 
     *
     * @param number the number
     * @return set of prime factors of {@code number}
     */

	public static Set<BigInteger> primeFactors(BigInteger number) {
		Set<BigInteger> factors = new HashSet<BigInteger>();
		while(number.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
			factors.add(BigInteger.valueOf(2));
			number = number.divide(BigInteger.valueOf(2));
		}
		BigInteger nextprime = BigInteger.valueOf(3);
		while (nextprime.pow(2).compareTo(number) <= 0) {
			while (number.mod(nextprime).equals(BigInteger.ZERO)){
				factors.add(nextprime);
				number = number.divide(nextprime);
			}
			nextprime = nextprime.nextProbablePrime();
		}
		if (number.compareTo(BigInteger.ONE) > 0 ) {
			factors.add(number);
		}
		return factors;
	}
}