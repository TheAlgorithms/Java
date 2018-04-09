/**
* Euclidean algorithm for finding the GCD of two numbers.
* Time complexity is O(log(max(A, B)))
*/
public class Euclidean_GCD
{
	public static int gcd(int a, int b)
	{
		/**
		* Iterative solution
		*/
		int res = 1;
		for ( ; a % b != 0; ) {
			res = a % b;
			a = b;
			b = res;
		}
		return res;
	}
}
