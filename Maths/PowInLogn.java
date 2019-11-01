package Maths;

public class PowInLogn {

	public static void main(String[] args) {
		System.out.println("4^5 = " + exp(4, 5));
		System.out.println("3^19 = " + exp(3, 19));
		System.out.println("5^13 = " + exp(5, 13));
		System.out.println("2^11 = " + exp(2, 11));
		System.out.println("2^41 = " + exp(2, 41));
	}

	/**
	 * Returns the value of the first argument raised to the power of the second
	 * argument
	 * 
	 * Runs in O(logn) time, while "naive" exponentiation has O(n) running time
	 * Check https://en.wikipedia.org/wiki/Exponentiation_by_squaring for more info.
	 *
	 * @param a the base.
	 * @param b the exponent.
	 * @return the value {@code a}<sup>{@code b}</sup>.
	 */
	static long exp(long a, int n) {
		long result = 1L;
		while (n > 0) {
			if (n % 2 == 1) {
				result *= a;
			}
			n /= 2;
			a = a * a;
		}
		return result;
	}

}