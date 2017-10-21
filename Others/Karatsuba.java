package Others;

/**
 *    Java Program to Implement Karatsuba Multiplication Algorithm.
 *    Mainly used for multiplication of large numbers.
 *    @author mani manasa mylavarapu
 **/

import java.util.Scanner;

/** Class Karatsuba **/
public class Karatsuba {
	/** Function to multiply two large numbers **/
	public long multiply(long x, long y) {
		int size1 = getNumberOfDigits(x);
		int size2 = getNumberOfDigits(y);
		int N = Math.max(size1, size2);
		if (N < 10)
			return x * y;
		N = (N / 2) + (N % 2);
		long m = (long) Math.pow(10, N);
		long b = x / m;
		long a = x - (b * m);
		long d = y / m;
		long c = y - (d * N);
		long z0 = multiply(a, c);
		long z1 = multiply(a + b, c + d);
		long z2 = multiply(b, d);

		return z0 + ((z1 - z0 - z2) * m) + (z2 * (long) (Math.pow(10, 2 * N)));
	}

	public int getNumberOfDigits(long num) {
		int ctr = 0;
		while (num != 0) {
			ctr++;
			num /= 10;
		}
		return ctr;
	}

	/** Main function for testing **/
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Karatsuba karat = new Karatsuba();

		System.out.println("Enter two numbers\n");
		long num1 = scan.nextLong();
		long num2 = scan.nextLong();
		long product = karat.multiply(num1, num2);
		System.out.println("\nProduct result is : " + product);
	}
}