package divideconquer;

import java.math.BigInteger;

/**
 * @author easy-brother
 * 
 * Find the Fibonacci sequence through the Recursive Squaring.
 * 
 * RecursiveSquaring class calculates the particular term of Fibonacci sequence.
 *
 * Time complexity: O(log(n)), because it is a divide and conquer algorithm
 * 
 */

public class Fibonacci_RecursiveSquaring {

	/**
	 * We can find the Fibonacci sequence more effectively than naive bottom-up algorithm 
	 * 
	 * We use One of the well-known properties of Fibonacci number is:
	 * 
	 * | F(n+1) ,  F(n)   |       | 1 , 1 | ^ n
	 * |                  |   =   |       |
	 * | F(n)   ,  F(n-1) |       | 1 , 0 |
	 *
	 * We can therefore compute the (n)th power of A = ((1,1),(1,0)) using recursive squaring
	 * 
	 */
	
	
	/**
	 * @return n(th) term of Fibonacci sequence
	 */
	public BigInteger getFibonacci(int n) {
		if (n < 2) { 
			BigInteger[][] big = new BigInteger[2][2];
			big[0][0] = BigInteger.valueOf(1);
			big[0][1] = BigInteger.valueOf(n);
			big[1][0] = BigInteger.valueOf(1);
			big[1][1] = BigInteger.valueOf(0);
			return big[0][1];
		}
		else {
			BigInteger[][] big = new BigInteger[2][2];
			big[0][0] = BigInteger.valueOf(1);
			big[0][1] = BigInteger.valueOf(1);
			big[1][0] = BigInteger.valueOf(1);
			big[1][1] = BigInteger.valueOf(0);
			return pow(big, n)[0][1];
		}
	}
	
	public BigInteger[][] pow(BigInteger[][] a, int n) {
		if (n == 1)
			return a;
		else if (n % 2 == 0) { // even number
			BigInteger[][] temp = pow(a, n / 2); 
			return mul(temp, temp);
		}
		else { // odd number
			BigInteger[][] temp = pow(a, (n - 1) / 2);
			BigInteger[][] temp2 = mul(temp, temp);
			return mul(temp2, a);
		}
	}
	
	/**
	 * Perform matrix multiplication
	 */
	public BigInteger[][] mul(BigInteger[][] a, BigInteger[][] b) {
		int n = 2;
		BigInteger[][] c = new BigInteger[2][2];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				c[i][j] = BigInteger.valueOf((long)0);
				for (int k = 0; k < n; k++) {
					c[i][j] = c[i][j].add(a[i][k].multiply(b[k][j]));
				}
			}
		}
		return c;
	}
}
