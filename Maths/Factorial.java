package Maths;

public class Factorial {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(n + "! = " + factorial(n));
    }

    /**
     * Calculate factorial
     *
     * @param n the number
     * @return the factorial of {@code n}
     */
    public static long factorial(int n) {
                    if (n == 1)
			return 1;

		int fnm = factorial(n - 1);

		int fn = fnm * n;

		return fn;
            }
}
